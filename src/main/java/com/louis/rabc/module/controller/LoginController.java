package com.louis.rabc.module.controller;

import com.louis.rabc.module.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("login")
    public UserVo login() {
        return null;
    }
}
