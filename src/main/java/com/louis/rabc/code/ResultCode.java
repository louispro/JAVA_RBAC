package com.louis.rabc.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResultCode {
    /**
     * 4开头的表示浏览器端错误
     * 5开头的表示服务器错误
     */

    SUCCESS(2000, "成功"),
    USER_NOT_FOUND(4001, "用户不存在"),
    LOGIN_EXPIRE(4002, "登录过期"),
    PASSWORD_ERROR(4003, "密码错误"),
    PERMISSION_DENY(4004, "权限不足"),
    PARAMETER_EXCEPTION(4005, "请求参数错误"),
    TOKEN_ERROR(4006, "令牌异常"),
    TOKEN_NULL(4007, "令牌为空"),
    TOKEN_BLACK(4008, "令牌无效"),
    AUTHCODE_ERROR(4009, "验证码错误"),
    AUTHCODE_EXPIRE(4010, "验证码过期"),
    SERVER_ERROR(5000, "服务器内部错误")



    ;

    private Integer code;
    private String message;

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
