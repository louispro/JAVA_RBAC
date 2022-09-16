package com.louis.rabc.module.auth.controller;

import com.louis.rabc.module.auth.entity.Auth;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixiaoyi
 * @since 2022/9/16 16:27
 **/
@RestController
@RequestMapping("rbac/auth")
@AllArgsConstructor
public class AuthController {

    private final Auth auth;


    /**
     * 得到公钥
     *
     * @return {@link String}
     */
    @PostMapping("publicKey")
    public String getPublicKey() {
        return auth.getPublicKey();
    }

}
