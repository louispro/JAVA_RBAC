package com.louis.rabc.module.user.controller;

import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.service.LoginService;
import com.louis.rabc.module.user.service.UserRoleService;
import com.louis.rabc.module.user.vo.UserVo;
import com.louis.rabc.utils.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("rbac")
@Slf4j
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;
    private UserRoleService userRoleService;

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
        //todo 全局统一异常处理
        //todo 全局统一响应格式
        request.getParameter("login");
        String requestParam = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestParam);
        User user = this.loginService.decryptCiphertext(requestParam);
        //赋予角色
        return true;
    }
}
