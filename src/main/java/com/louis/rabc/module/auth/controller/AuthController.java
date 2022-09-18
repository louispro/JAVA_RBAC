package com.louis.rabc.module.auth.controller;

import com.louis.rabc.annotation.AuthAndResponseUnify;
import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixiaoyi
 * @since 2022/9/16 16:27
 **/
@RestController
@RequestMapping("auth")
@AllArgsConstructor
@Api(tags = "加密相关")
@Slf4j
public class AuthController {

    private final AuthService authService;


    /**
     * 得到公钥
     *
     * @return {@link String}
     */
    @PostMapping("publicKey")
    @ApiOperation("获取公钥")
    @AuthAndResponseUnify(isAuthentication = false, isAuthorization = false)
    public Object getPublicKey() {
        Auth auth = authService.getById(1);
        return auth.getPublicKey();
    }

}
