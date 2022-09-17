package com.louis.rabc.module.user.dto;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户更新dto
 *
 * @author louis
 * @date 2022/09/17
 */
@Getter
@Setter
public class UserUpdateDto {
    @NotNull
    private Long id;

    private String username;

    private String phone;

    private String nickname;

    private String address;

    private String age;

    private String sex;
}
