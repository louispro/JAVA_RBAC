package com.louis.rabc.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.user.dao.RoleDao;
import com.louis.rabc.module.user.entity.Role;
import com.louis.rabc.module.user.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:22
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}

