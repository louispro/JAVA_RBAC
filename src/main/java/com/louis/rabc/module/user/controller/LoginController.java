package com.louis.rabc.module.user.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.louis.rabc.annotation.AuthAndResponseUnify;
import com.louis.rabc.module.user.dto.UserLoginDto;
import com.louis.rabc.module.user.entity.UserRole;
import com.louis.rabc.module.user.service.LoginService;
import com.louis.rabc.module.user.service.UserRoleService;
import com.louis.rabc.module.user.vo.UserVo;
import com.louis.rabc.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
@Api(tags = "登录与注册")
public class LoginController {

    private final LoginService loginService;
    private final UserRoleService userRoleService;

    /**
     * 请求参数：username,authCode,ciphertext,其中ciphertext为password,mail,timeMillis以json格式加密得到
     * 请求格式：JSON
     * 注册
     *
     * @param request 请求
     * @return {@link String}
     */
    @ApiOperation("注册")
    @PostMapping("register")
    @AuthAndResponseUnify(isAuthentication = false, isAuthorization = false)
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
     * 请求参数：ciphertext,其中ciphertext为mail,timeMillis以json格式加密得到
     * 参数格式：json
     * 获得身份验证代码
     */
    @ApiOperation("获取验证码")
    @PostMapping("authCode")
    @AuthAndResponseUnify(isAuthentication = false, isAuthorization = false)
    public void getAuthCode(HttpServletRequest request) {
        this.loginService.getAuthCode(request);
    }

    /**
     * 登录
     *
     * @return {@link UserVo}
     */
    @ApiOperation("通过密码登录")
    @PostMapping("loginByPassword")
    @AuthAndResponseUnify(isAuthentication = false, isAuthorization = false)
    public Map<String, String> loginByPassword(@RequestBody UserLoginDto dto) {
        return this.loginService.loginByPassword(dto);
    }

    @ApiOperation("通过邮箱登录")
    @PostMapping("loginByMail")
    @AuthAndResponseUnify(isAuthentication = false, isAuthorization = false)
    public Map<String, String> loginByMail(HttpServletRequest request) {
//        String authCode = String.valueOf((int)((Math.random() * 9 + 1) * Math.pow(10,5)));
        String requestJson = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestJson);
        String authCode = JSONUtil.parseObj(requestJson).get("authCode", String.class);
        String ciphertext = JSONUtil.parseObj(requestJson).get("ciphertext", String.class);
        String clearJson = this.loginService.decryptCiphertext(ciphertext);
        String mail = JSONUtil.parseObj(clearJson).get("mail", String.class);
        return loginService.loginByMail(mail, authCode);
    }

    /**
     * 注销
     *
     * @return {@link Boolean}
     */
    @ApiOperation("注销")
    @PostMapping("logout")
    @AuthAndResponseUnify(isAuthorization = false)
    public Boolean logout(HttpServletRequest request) {
        return this.loginService.logout(request);
    }


}
