package com.louis.rabc.annotation;

import java.lang.annotation.*;

/**
 * 身份验证和响应统一
 *
 * @author louis
 * @date 2022/09/17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AuthAndResponseUnify {
    /**
     * 是否需要进行身份验证
     *
     * @return boolean
     */
    boolean isAuthentication() default true;

    boolean isAuthorization() default true;
}
