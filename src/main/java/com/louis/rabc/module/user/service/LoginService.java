package com.louis.rabc.module.user.service;

import cn.hutool.json.JSONObject;
import com.louis.rabc.module.user.dto.UserLoginDto;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    /**
     * 解密密文
     *
     * @param ciphertext 密文
     * @return {@link String}
     */
    String decryptCiphertext(String ciphertext);

    /**
     * 注册用户
     *
     * @param decrypt     明文
     * @param requestJson json请求
     * @return {@link Boolean}
     */
    Boolean register(JSONObject requestJson, String decrypt);

    /**
     * 登录通过密码
     *
     * @param dto 用户名和密码
     * @return {@link String}
     */
    String loginByPassword(UserLoginDto dto);

    /**
     * 检查令牌
     *
     * @param token 令牌
     * @return {@link Boolean}
     */
    Boolean checkToken(String token);

    /**
     * 注销
     *
     * @param request 请求
     * @return {@link Boolean}
     */
    Boolean logout(HttpServletRequest request);

    /**
     * 登录通过邮件
     *
     * @param mail 邮件
     * @return {@link String}
     */
    String loginByMail(String mail, String authCode);

    /**
     * md5密码
     *
     * @param password 密码
     * @return {@link String}
     */
    String passwordMd5(String password);

    /**
     * 获得身份验证代码
     *
     * @param request 请求
     */
    void getAuthCode(HttpServletRequest request);
}
