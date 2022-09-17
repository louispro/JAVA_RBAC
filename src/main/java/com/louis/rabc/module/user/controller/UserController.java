package com.louis.rabc.module.user.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.louis.rabc.annotation.AuthAndResponseUnify;
import com.louis.rabc.code.ResultCode;
import com.louis.rabc.exception.BusinessException;
import com.louis.rabc.module.user.dto.UserUpdateDto;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.service.LoginService;
import com.louis.rabc.module.user.service.UserService;
import com.louis.rabc.utils.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author laixiaoyi
 * @since 2022/9/16 10:49
 **/
@RestController
@RequestMapping("user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final LoginService loginService;
    private RSA rsa;

    /**
     * 重置密码
     *
     * @return {@link String}
     */
    @PostMapping("resetPwd")
    @AuthAndResponseUnify
    public Boolean resetPassword(HttpServletRequest request) {
        String requestJson = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestJson);
        String authCode = JSONUtil.parseObj(requestJson).get("authCode", String.class);
        if ("338166".equals(authCode)) {
            throw new BusinessException(ResultCode.AUTHCODE_ERROR);
        }
        String ciphertext = JSONUtil.parseObj(requestJson).get("ciphertext", String.class);
        String clearJson = this.loginService.decryptCiphertext(ciphertext);
        String mail = JSONUtil.parseObj(clearJson).get("mail", String.class);
        String oldPassword = JSONUtil.parseObj(clearJson).get("oldPassword", String.class);
        String newPassword = JSONUtil.parseObj(clearJson).get("newPassword", String.class);
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getMail, mail));
        String password = user.getPassword();
        if (!password.equals(this.loginService.passwordMd5(oldPassword))) {
            throw new BusinessException(ResultCode.PASSWORD_ERROR);
        }
        return this.userService.update(Wrappers.<User>lambdaUpdate().set(User::getPassword, this.loginService.passwordMd5(newPassword)).eq(User::getId, user.getId()));
    }

    @PostMapping("forgetPassword")
    @AuthAndResponseUnify(isAuth = false)
    public Boolean forgetPassword(HttpServletRequest request) {
        String requestJson = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestJson);
        String authCode = JSONUtil.parseObj(requestJson).get("authCode", String.class);
        if ("338166".equals(authCode)) {
            throw new BusinessException(ResultCode.AUTHCODE_ERROR);
        }
        String ciphertext = JSONUtil.parseObj(requestJson).get("ciphertext", String.class);
        String clearJson = this.loginService.decryptCiphertext(ciphertext);
        String mail = JSONUtil.parseObj(clearJson).get("mail", String.class);
        String newPassword = JSONUtil.parseObj(clearJson).get("newPassword", String.class);
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getMail, mail));
        return this.userService.update(Wrappers.<User>lambdaUpdate().set(User::getPassword, this.loginService.passwordMd5(newPassword)).eq(User::getId, user.getId()));
    }

    /**
     * 更新用户信息
     *
     * @return {@link User}
     */
    @PostMapping("update/{id}")
    @AuthAndResponseUnify
    public User update(@RequestBody UserUpdateDto dto) {
        return null;
    }

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @return {@link Boolean}
     */
    @GetMapping("username")
    @AuthAndResponseUnify(isAuth = false)
    public Boolean isUsernameExist(@RequestParam("username") String username) {
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        if (ObjectUtil.isEmpty(user)) {
            return false;
        }
        return true;
    }

    /**
     * 是手机存在
     * 手机号是否存在
     *
     * @param request 请求
     * @return {@link Boolean}
     */
    @PostMapping("phone")
    @AuthAndResponseUnify(isAuth = false)
    public Boolean isPhoneExist(HttpServletRequest request) {
        String requestJson = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestJson);
        String ciphertext = JSONUtil.parseObj(requestJson).get("ciphertext", String.class);
        String clearJson = this.loginService.decryptCiphertext(ciphertext);
        String mail = JSONUtil.parseObj(clearJson).get("mail", String.class);
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getMail, mail));
        if (ObjectUtil.isEmpty(user)) {
            return false;
        }
        return true;
    }

}
