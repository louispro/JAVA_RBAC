package com.louis.rabc.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.rabc.module.user.entity.RolePermission;

import java.util.List;
import java.util.Set;

/**
 * (RolePermission)表服务接口
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:22
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 获取用户能够访问的资源
     *
     * @param roles 角色
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissions(String roles);
}

