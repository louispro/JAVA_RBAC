package com.louis.rabc.module.user.controller;

import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixiaoyi
 * @since 2022/9/16 10:49
 **/
@RestController
@RequestMapping("rbac/user")
public class UserController {

    /**
     * 重置密码
     *
     * @return {@link String}
     */
    @PostMapping("resetPwd")
    public String resetPassword() {
        return null;
    }

    /**
     * 忘记密码
     *
     * @return {@link String}
     */
    @PostMapping("forgetPwd")
    public String forgetPassword() {
        return null;
    }

    /**
     * 更新用户信息
     *
     * @return {@link User}
     */
    @PostMapping("update/{id}")
    public User update() {
        return null;
    }

}
