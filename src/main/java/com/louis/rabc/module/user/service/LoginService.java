package com.louis.rabc.module.user.service;

import cn.hutool.json.JSONObject;
import com.louis.rabc.module.user.dto.UserLoginDto;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.vo.UserVo;

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
     * 登录
     *
     * @param dto 用户名和密码
     * @return {@link UserVo}
     */
    String login(UserLoginDto dto);

    /**
     * 检查令牌
     *
     * @param token 令牌
     * @return {@link Boolean}
     */
    Boolean checkToken(String token);
}
