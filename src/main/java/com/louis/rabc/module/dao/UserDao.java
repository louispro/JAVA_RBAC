package com.louis.rabc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.rabc.module.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:22
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

