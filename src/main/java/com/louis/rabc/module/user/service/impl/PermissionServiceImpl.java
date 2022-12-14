package com.louis.rabc.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.user.dao.PermissionDao;
import com.louis.rabc.module.user.entity.Permission;
import com.louis.rabc.module.user.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * (Permission)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:21
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {

}

