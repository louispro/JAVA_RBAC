package com.louis.rabc.module.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;

/**
 * (Resource)表实体类
 *
 * @author 赖小燚
 * @since 2022-09-18 17:23:03
 */
@SuppressWarnings("serial")
@Data
public class Resource extends Model<Resource> {

    /**
     * 主键
     */
    private String id;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 资源种类
     */
    private String resourceSpecies;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Integer deleteState;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}

