package com.louis.rabc.module.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.rabc.module.user.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Resource)表数据库访问层
 *
 * @author 赖小燚
 * @since 2022-09-18 17:23:02
 */
@Mapper
public interface ResourceDao extends BaseMapper<Resource> {

}

