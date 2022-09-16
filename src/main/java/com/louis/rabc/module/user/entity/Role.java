package com.louis.rabc.module.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;

/**
 * (Role)表实体类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:21
 */
@SuppressWarnings("serial")
@Data
public class Role extends Model<Role> {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除，0删除，1已删除
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

