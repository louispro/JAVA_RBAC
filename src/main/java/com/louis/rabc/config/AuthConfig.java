package com.louis.rabc.config;

import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author laixiaoyi
 * @since 2022/9/16 12:24
 **/
@Configuration
@AllArgsConstructor
public class AuthConfig {

    private final AuthService authService;

    @Bean
    public Auth auth() {
        return authService.getById(1);
    }
}
