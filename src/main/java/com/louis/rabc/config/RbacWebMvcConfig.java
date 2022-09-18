package com.louis.rabc.config;

import com.louis.rabc.interceptor.ResponseResultInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class RbacWebMvcConfig implements WebMvcConfigurer {

    private final ResponseResultInterceptor responseResultInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseResultInterceptor).addPathPatterns("/**");
    }
}
