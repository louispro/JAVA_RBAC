package com.louis.rabc.module.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.rabc.module.auth.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Keys)表数据库访问层
 *
 * @author 赖小燚
 * @since 2022-09-16 12:22:40
 */
@Mapper
public interface AuthDao extends BaseMapper<Auth> {

}

