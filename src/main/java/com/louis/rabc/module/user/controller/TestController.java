package com.louis.rabc.module.user.controller;

import com.louis.rabc.annotation.AuthAndResponseUnify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试")
@RestController
@RequestMapping("test")
public class TestController {

    @ApiOperation("测试统一响应格式")
    @PostMapping("test")
    public String test() {
        return "RBAC权限管理";
    }


    @ApiOperation("测试http重定向至https")
    @GetMapping("http2https")
    public String http2https() {
        return "http重定向至https";
    }

    /**
     * 统一异常处理测试
     *
     * @return {@link String}
     */
    @ApiOperation("统一异常处理测试")
    @PostMapping("exception")
    public String exception() {
        Object obj = null;
        obj.toString();
        return null;
    }
}
