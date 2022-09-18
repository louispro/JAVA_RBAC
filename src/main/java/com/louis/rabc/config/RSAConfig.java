package com.louis.rabc.config;

import cn.hutool.crypto.asymmetric.RSA;
import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.auth.service.AuthService;
import com.louis.rabc.module.auth.service.impl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 身份验证配置
 *
 * @author laixiaoyi
 * @date 2022/09/17
 * @since 2022/9/16 12:24
 */
@Configuration
@AllArgsConstructor
public class RSAConfig {

    private final AuthService authService;

    @Bean
    public RSA rsa() {
        Auth auth = authService.getById(1);
        return new RSA(auth.getPrivateKey(), auth.getPublicKey());
    }
}
