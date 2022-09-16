package com.louis.rabc.module.user.service.impl;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.service.LoginService;
import com.louis.rabc.module.user.service.UserService;
import com.louis.rabc.utils.RSAUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author laixiaoyi
 * @since 2022/9/16 13:48
 **/
@Service
@AllArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final Auth auth;
    private UserService userService;

    @Override
    public User decryptCiphertext(String requestParam) {
        JSONObject requestJson = JSONUtil.parseObj(requestParam);
        //获取密文
        String ciphertext = requestJson.get("ciphertext", String.class);
        log.info("私钥 ===》 {}", auth.getPrivateKey());
        log.info("公钥 ===> {}", auth.getPublicKey());
        RSA rsa = new RSA(auth.getPrivateKey(), auth.getPublicKey());
        String cipherJson = rsa.decryptStr(ciphertext, KeyType.PrivateKey);
        JSONObject clearJson = JSONUtil.parseObj(cipherJson);
        String password = clearJson.get("password", String.class);
        //密码加密
        String passwordDigest = DigestUtil.sha256(password).toString();
        User user = new User();
        user.setUsername(requestJson.get("username", String.class));
        user.setPassword(passwordDigest);
        user.setCreateTime(new Date());
        this.userService.save(user);
        return user;
    }
}
