package com.louis.rabc.module.user.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.louis.rabc.annotation.ResponseUnify;
import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.service.UserService;
import com.louis.rabc.module.user.vo.UserVo;
import com.louis.rabc.utils.RSAUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    private final Auth auth;

    /**
     * 重置密码
     *
     * @return {@link String}
     */
    @PostMapping("resetPwd")
    public String resetPassword() {
        return null;
    }

    /**
     * 忘记密码
     *
     * @return {@link String}
     */
    @PostMapping("forgetPwd")
    public String forgetPassword() {
        return null;
    }

    /**
     * 更新用户信息
     *
     * @return {@link User}
     */
    @PostMapping("update/{id}")
    public User update() {
        return null;
    }

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @return {@link Boolean}
     */
    @GetMapping("username")
    @ResponseUnify
    public Boolean isUsernameExist(@RequestParam("username") String username) {
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        if (ObjectUtil.isEmpty(user)) {
            return false;
        }
        return true;
    }

    /**
     * 手机号是否存在
     *
     * @param phoneEncrypt 手机加密
     * @return {@link Boolean}
     */
    @PostMapping("phone")
    @ResponseUnify
    public Boolean isPhoneExist(@RequestBody String phoneEncrypt) {
//        String phone = RSAUtil.decrypt(phoneEncrypt, auth.getPrivateKey());
        JSONObject jsonObject = JSONUtil.parseObj(phoneEncrypt);
        phoneEncrypt = jsonObject.get("phone", String.class);
        log.info("privateKey ===> {}", auth.getPrivateKey());
        log.info("publicKey ===> {}", auth.getPublicKey());
        log.info("phoneEncrypt ===> {}", phoneEncrypt);
        RSA rsa = new RSA(auth.getPrivateKey(), auth.getPublicKey());
        String phone = rsa.decryptStr(phoneEncrypt, KeyType.PrivateKey);
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getPhone, phone));
        if (ObjectUtil.isEmpty(user)) {
            return false;
        }
        return true;
    }

}
