package com.louis.rabc.module.user.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;

/**
 * (UserRole)表实体类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:23
 */
@SuppressWarnings("serial")
@Data
public class UserRole extends Model<UserRole> {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.userId;
    }
}

