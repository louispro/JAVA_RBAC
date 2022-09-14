package com.louis.rabc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.rabc.module.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Permission)表数据库访问层
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:21
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {

}

