package com.louis.rabc.module.user.controller;

import com.louis.rabc.annotation.ResponseUnify;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping("test")
    public String test() {
        return "RBAC权限管理";
    }

    @GetMapping("http2https")
    @ResponseUnify
    public String http2https() {
        return "http重定向至https";
    }

    /**
     * 统一异常处理测试
     *
     * @return {@link String}
     */
    @PostMapping("exception")
    public String exception() {
        Object obj = null;
        obj.toString();
        return null;
    }
}
