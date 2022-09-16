package com.louis.rabc.module.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.rabc.module.auth.entity.Keys;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Keys)表数据库访问层
 *
 * @author 赖小燚
 * @since 2022-09-16 12:22:40
 */
@Mapper
public interface KeysDao extends BaseMapper<Keys> {

}

