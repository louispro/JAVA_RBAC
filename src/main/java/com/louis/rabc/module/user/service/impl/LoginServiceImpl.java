package com.louis.rabc.module.user.service.impl;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.louis.rabc.code.ResultCode;
import com.louis.rabc.exception.BusinessException;
import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.auth.entity.BlackToken;
import com.louis.rabc.module.auth.entity.QQMail;
import com.louis.rabc.module.auth.service.AuthService;
import com.louis.rabc.module.auth.service.BlackTokenService;
import com.louis.rabc.module.user.dto.UserLoginDto;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.entity.UserRole;
import com.louis.rabc.module.user.service.LoginService;
import com.louis.rabc.module.user.service.UserRoleService;
import com.louis.rabc.module.user.service.UserService;
import com.louis.rabc.utils.HttpUtil;
import com.louis.rabc.utils.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author laixiaoyi
 * @since 2022/9/16 13:48
 **/
@Service
@AllArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private UserService userService;
    private UserRoleService userRoleService;
    private RSA rsa;
    private BlackTokenService blackTokenService;
    private StringRedisTemplate stringRedisTemplate;
    private AuthService authService;

    @Override
    public String decryptCiphertext(String ciphertext) {
        log.info("ciphertext ===> {}", ciphertext);
        return this.rsa.decryptStr(ciphertext, KeyType.PrivateKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(JSONObject requestJson, String decrypt) {
        //todo 用户名和密码参数校验
        //todo 验证用户和手机号是否存在
        //todo redis存储验证码
        String username = requestJson.get("username", String.class);
        String authCode = requestJson.get("authCode", String.class);
        JSONObject clearJson = JSONUtil.parseObj(decrypt);
        String password = clearJson.get("password", String.class);
        String mail = clearJson.get("mail", String.class);
        String redisAuthCode = stringRedisTemplate.opsForValue().get(mail);
        if (StrUtil.isBlank(redisAuthCode)) {
            throw new BusinessException(ResultCode.AUTHCODE_EXPIRE);
        }
        if (!redisAuthCode.equals(authCode)) {
            throw new BusinessException(ResultCode.AUTHCODE_ERROR);
        }
        //密码加密
        this.userService.createUser(username, mail, this.passwordMd5(password));
        return userRoleService.addUserRole(username);
    }

    @Override
    public Map<String, String> loginByPassword(UserLoginDto dto) {
        //todo 用户名和密码参数校验
        String username = dto.getUsername();
        //解密密文获取密码
        String ciphertext = dto.getCiphertext();
        String passwordDecrypt = this.decryptCiphertext(ciphertext);
        String password = JSONUtil.parseObj(passwordDecrypt).get("password", String.class);
        if (StrUtil.isBlank(username) && StrUtil.isBlank(username)) {
            throw new BusinessException(ResultCode.PARAMETER_EXCEPTION);
        }
        String passwordDigest = this.passwordMd5(password);
        log.info("passwordDigest === > {}", passwordDigest);
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        if (!user.getPassword().equals(passwordDigest)) {
            throw new BusinessException(ResultCode.PASSWORD_ERROR);
        }
        List<UserRole> userRoleList = this.userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, user.getId()));
        //讲角色信息转换为JSON
        List<Long> roleList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        String rolesJson = JSONUtil.toJsonStr(roleList);
        Map<String, String> map = new HashMap<>(2);
        map.put("roles", rolesJson);
        map.put("token", JWTUtil.generateToken(user.getUsername(), rolesJson));
        return map;
    }

    @Override
    public Boolean checkToken(String token) {
        return false;
    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        BlackToken blackToken = new BlackToken();
        blackToken.setToken(request.getHeader("token"));
        blackToken.setCreateTime(new Date());
        return this.blackTokenService.save(blackToken);
    }

    @Override
    public Map<String, String> loginByMail(String mail, String authCode) {
        log.info("验证码前端输入为： ===> {}", authCode);
        String redisAuthCode = stringRedisTemplate.opsForValue().get(mail);
        if (StrUtil.isBlank(redisAuthCode)) {
            throw new BusinessException(ResultCode.AUTHCODE_EXPIRE);
        }
        if (!authCode.equals(redisAuthCode)) {
            throw new BusinessException(ResultCode.AUTHCODE_ERROR);
        }
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getMail, mail));
        if (ObjectUtil.isEmpty(user)) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        List<UserRole> userRoleList = this.userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, user.getId()));
        //讲角色信息转换为JSON
        List<Long> roleList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        String rolesJson = JSONUtil.toJsonStr(roleList);
        Map<String, String> map = new HashMap<>(2);
        map.put("roles", rolesJson);
        log.info("roles ===> {}", rolesJson);
        map.put("token", JWTUtil.generateToken(user.getUsername(), rolesJson));
        return map;
    }

    /**
     * md5密码
     *
     * @param password 密码
     * @return {@link String}
     */
    public String passwordMd5(String password) {
        //密码加密
        MD5 md5 = new MD5();
        return md5.digestHex(password);
    }

    @Override
    public void getAuthCode(HttpServletRequest request) {
        //生成6位数验证码
//        String authCode = String.valueOf((int)((Math.random() * 9 + 1) * Math.pow(10,5)));
        String authCode = "338166";
        String requestJson = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestJson);
        String ciphertext = JSONUtil.parseObj(requestJson).get("ciphertext", String.class);
        String clearJson = this.decryptCiphertext(ciphertext);
        String mail = JSONUtil.parseObj(clearJson).get("mail", String.class);
        //将验证码存储到redis中，2分钟过期
        this.stringRedisTemplate.opsForValue().set(mail, authCode, 3600, TimeUnit.SECONDS);
        QQMail qqMail = new QQMail();
        log.info("mail ===> {}", mail);
        qqMail.setAcceptMailAccount(mail);
        qqMail.setTheme("验证码");
        //todo 替换成authCode
        qqMail.setMailText("338166");
        this.authService.sendQQMail(qqMail);
    }
}
