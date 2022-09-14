package com.louis.rabc.module.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("test")
    public String test() {
        return "RBAC权限管理";
    }

    @GetMapping("http2https")
    public String http2https() {
        return "http重定向至https";
    }
}
