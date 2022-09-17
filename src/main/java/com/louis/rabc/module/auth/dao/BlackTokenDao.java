package com.louis.rabc.module.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.rabc.module.auth.entity.BlackToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlackToken)表数据库访问层
 *
 * @author 赖小燚
 * @since 2022-09-18 00:21:05
 */
@Mapper
public interface BlackTokenDao extends BaseMapper<BlackToken> {

}

