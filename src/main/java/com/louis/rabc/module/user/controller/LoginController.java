package com.louis.rabc.module.user.controller;

import com.louis.rabc.module.user.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("login")
    public UserVo login() {
        return null;
    }
}
