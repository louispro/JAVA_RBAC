package com.louis.rabc.module.user.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;

/**
 * (RolePermission)表实体类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:22
 */
@SuppressWarnings("serial")
@Data
public class RolePermission extends Model<RolePermission> {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 资源id
     */
    private String permissionId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.roleId;
    }
}

