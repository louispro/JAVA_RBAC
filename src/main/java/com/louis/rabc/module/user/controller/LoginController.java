package com.louis.rabc.module.user.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.louis.rabc.annotation.AuthAndResponseUnify;
import com.louis.rabc.module.auth.entity.QQMail;
import com.louis.rabc.module.auth.service.AuthService;
import com.louis.rabc.module.user.dto.UserLoginDto;
import com.louis.rabc.module.user.service.LoginService;
import com.louis.rabc.module.user.vo.UserVo;
import com.louis.rabc.utils.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;
    private AuthService authService;

    /**
     * 请求参数：username,authCode,ciphertext,其中ciphertext为password,mail,timeMillis以json格式加密得到
     * 请求格式：JSON
     * 注册
     *
     * @param request 请求
     * @return {@link String}
     */
    @PostMapping("register")
    @AuthAndResponseUnify(isAuth = false)
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
    @PostMapping("authCode")
    @AuthAndResponseUnify(isAuth = false)
    public void getAuthCode(HttpServletRequest request) {
        //生成6位数验证码
        String authCode = String.valueOf((int)((Math.random() * 9 + 1) * Math.pow(10,5)));
        String requestJson = HttpUtil.readData(request);
        log.info("requestParam ===> {}", requestJson);
        String ciphertext = JSONUtil.parseObj(requestJson).get("ciphertext", String.class);
        String clearJson = this.loginService.decryptCiphertext(ciphertext);
        String mail = JSONUtil.parseObj(clearJson).get("mail", String.class);
        QQMail qqMail = new QQMail();
        log.info("mail ===> {}", mail);
        qqMail.setAcceptMailAccount(mail);
        qqMail.setTheme("验证码");
        //todo 替换成authCode
        qqMail.setMailText("338166");
        this.authService.sendQQMail(qqMail);
    }

    /**
     * 登录
     *
     * @return {@link UserVo}
     */
    @PostMapping("loginByPassword")
    @AuthAndResponseUnify(isAuth = false)
    public String loginByPassword(@RequestBody UserLoginDto dto) {
        return loginService.loginByPassword(dto);
    }

    @PostMapping("loginByMail")
    @AuthAndResponseUnify(isAuth = false)
    public Boolean loginByMail(HttpServletRequest request) {
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
    @PostMapping("logout")
    @AuthAndResponseUnify
    public Boolean logout(HttpServletRequest request, @RequestBody Long id) {
        return this.loginService.logout(request);
    }


}
