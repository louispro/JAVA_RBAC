package com.louis.rabc.module.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.user.dao.RolePermissionDao;
import com.louis.rabc.module.user.entity.Permission;
import com.louis.rabc.module.user.entity.RolePermission;
import com.louis.rabc.module.user.service.PermissionService;
import com.louis.rabc.module.user.service.RolePermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (RolePermission)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:22
 */
@Service("rolePermissionService")
@AllArgsConstructor
@Slf4j
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {

    private final PermissionService permissionService;

    @Override
    public List<String> getPermissions(String roles) {
        List<Long> roleList = Arrays.asList(StrUtil.removeSuffix(StrUtil.removePrefix(roles,"["), "]").split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
        roleList.forEach(System.out::println);
        Set<Long> permissionSet = new HashSet<>();
        List<RolePermission> rolePermissionList = this.list(Wrappers.<RolePermission>lambdaQuery().in(RolePermission::getRoleId, roleList));
        rolePermissionList.forEach(rolePermission -> {
            permissionSet.add(rolePermission.getPermissionId());
        });
        List<Permission> permissionList = permissionService.list(Wrappers.<Permission>lambdaQuery().in(Permission::getId, permissionSet));
        List<String> pathList = permissionList.stream().map(Permission::getPath).collect(Collectors.toList());
//        log.info("pathList ===> {}", pathList);
        return pathList;
    }
}

