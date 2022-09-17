package com.louis.rabc.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.rabc.module.user.entity.UserRole;

/**
 * (UserRole)表服务接口
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:23
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 添加用户角色
     *
     * @param username 用户名
     * @return {@link Boolean}
     */
    Boolean addUserRole(String username);
}

