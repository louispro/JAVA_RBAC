package com.louis.rabc.module.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.rabc.module.user.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * (UserRole)表数据库访问层
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:23
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRole> {

}

