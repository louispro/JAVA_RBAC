package com.louis.rabc.module.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.user.dao.UserRoleDao;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.entity.UserRole;
import com.louis.rabc.module.user.service.UserRoleService;
import com.louis.rabc.module.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:23
 */
@AllArgsConstructor
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

    private final UserService userService;

    @Override
    public Boolean addUserRole(String username) {
        Long userId = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username)).getId();
        //赋予角色
        UserRole userRole =  new UserRole();
        userRole.setUserId(userId);
        //新注册用户默认为普通用户
        userRole.setRoleId(6L);
        return this.baseMapper.insert(userRole) > 0;
    }
}

