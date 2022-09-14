package com.louis.rabc.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.dao.RolePermissionDao;
import com.louis.rabc.module.entity.RolePermission;
import com.louis.rabc.module.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * (RolePermission)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:22
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {

}

