package com.louis.rabc.module.user.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.louis.rabc.annotation.ResponseUnify;
import com.louis.rabc.module.user.dto.UserLoginDto;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.entity.UserRole;
import com.louis.rabc.module.user.service.LoginService;
import com.louis.rabc.module.user.service.UserRoleService;
import com.louis.rabc.module.user.vo.UserVo;
import com.louis.rabc.utils.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    /**
     * 注册
     *
     * @param request 请求
     * @return {@link String}
     */
    @PostMapping("register")
    @ResponseUnify
    public Boolean register(HttpServletRequest request) {
        String requestParam = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestParam);
        JSONObject requestJson = JSONUtil.parseObj(requestParam);
        //获取密文
        String ciphertext = requestJson.get("ciphertext", String.class);
        String clearJson = this.loginService.decryptCiphertext(ciphertext);
        return this.loginService.register(requestJson, clearJson);
    }

    /**
     * 登录
     *
     * @return {@link UserVo}
     */
    @PostMapping("login")
    @ResponseUnify
    public String login(@RequestBody UserLoginDto dto) {
        return loginService.login(dto);
    }

    /**
     * 注销
     *
     * @return {@link Boolean}
     */
    @PostMapping("logout")
    public Boolean logout() {
        return false;
    }


}
