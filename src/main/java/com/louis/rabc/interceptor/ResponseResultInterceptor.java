package com.louis.rabc.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.louis.rabc.annotation.AuthAndResponseUnify;
import com.louis.rabc.code.ResultCode;
import com.louis.rabc.exception.BusinessException;
import com.louis.rabc.module.auth.entity.BlackToken;
import com.louis.rabc.module.auth.service.BlackTokenService;
import com.louis.rabc.module.user.entity.RolePermission;
import com.louis.rabc.module.user.service.RolePermissionService;
import com.louis.rabc.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class ResponseResultInterceptor implements HandlerInterceptor {

    /* 使用统一返回体的标识 */
    private static final String RESPONSE_RESULT_ANNOTATION = "RESPONSE-RESULT-ANNOTATION";
    private BlackTokenService blackTokenService;
    private RolePermissionService rolePermissionService;

    public ResponseResultInterceptor(RolePermissionService rolePermissionService, BlackTokenService blackTokenService) {
        this.blackTokenService = blackTokenService;
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 正在处理请求的方法bean
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取当前类
            final Class<?> clazz = handlerMethod.getBeanType();
            // 获取当前方法
            final Method method = handlerMethod.getMethod();
            response.setContentType("application/json");
            // 判断是否在类对象上加了注解
            if (clazz.isAnnotationPresent(AuthAndResponseUnify.class)) {
                // 设置该请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANNOTATION, clazz.getAnnotation(AuthAndResponseUnify.class));
            }
            // 判断是否在方法上加了注解
            else if (method.isAnnotationPresent(AuthAndResponseUnify.class)) {
                AuthAndResponseUnify authAndResponseUnify = method.getAnnotation(AuthAndResponseUnify.class);
                String roles = null;
                //身份验证
                if (authAndResponseUnify.isAuthentication()) {
                    String token = request.getHeader("token");
                    roles = this.checkToken(token);
                }
                //权限验证
                if (authAndResponseUnify.isAuthorization()) {
                    String path = request.getServletPath();
                    log.info("用户角色 ===》 {}", roles);
                    //获取用户的所有权限
                    List<String> pathList = this.rolePermissionService.getPermissions(roles);
                    log.info("用户权限 ---> {}", pathList);
                    log.info("用户访问资源 ===> {}", path);
                    if (!pathList.contains(path)) {
                        throw new BusinessException(ResultCode.PERMISSION_DENY);
                    }
                }
                // 设置该请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANNOTATION, method.getAnnotation(AuthAndResponseUnify.class));
            }
        }
        return true;
    }

    private String checkToken(String token) {
        if (StrUtil.isBlank(token)) {
            throw new BusinessException(ResultCode.TOKEN_NULL);
        }
        //验证用户是否注销
        BlackToken blackToken = this.blackTokenService.getOne(Wrappers.<BlackToken>lambdaQuery().eq(BlackToken::getToken, token));
        if (!ObjectUtil.isEmpty(blackToken)) {
            throw new BusinessException(ResultCode.TOKEN_BLACK);
        }
        try {
            return JWTUtil.checkToken(token);
        } catch (Exception exception) {
            throw new BusinessException(ResultCode.TOKEN_ERROR);
        }
    }
}
