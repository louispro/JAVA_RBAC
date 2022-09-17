package com.louis.rabc.module.user.dto;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {

    /**
     * 用户名
     */
    @NotNull
    private String username;

    /**
     * 密码加密
     */
    private String ciphertext;
}
