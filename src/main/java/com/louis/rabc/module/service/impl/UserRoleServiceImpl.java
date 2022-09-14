package com.louis.rabc.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.dao.UserRoleDao;
import com.louis.rabc.module.entity.UserRole;
import com.louis.rabc.module.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:23
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}

