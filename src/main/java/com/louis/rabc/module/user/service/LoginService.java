package com.louis.rabc.module.user.service;

import com.louis.rabc.module.user.entity.User;

public interface LoginService {
    /**
     * 解密密文
     *
     * @param requestParam 请求参数
     * @return {@link User}
     */
    User decryptCiphertext(String requestParam);
}
