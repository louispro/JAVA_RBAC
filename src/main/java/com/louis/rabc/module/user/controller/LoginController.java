package com.louis.rabc.module.user.controller;

import com.louis.rabc.module.user.vo.UserVo;
import com.louis.rabc.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("rbac")
@Slf4j
public class LoginController {

    /**
     * 登录
     *
     * @return {@link UserVo}
     */
    @PostMapping("login")
    public UserVo login() {
        return null;
    }

    /**
     * 注册
     *
     * @param request 请求
     * @return {@link String}
     */
    @PostMapping("register")
    public Boolean register(HttpServletRequest request) {
        String requestParam = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestParam);
        return null;
    }
}
