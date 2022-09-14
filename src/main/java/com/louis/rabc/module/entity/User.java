package com.louis.rabc.module.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;

/**
 * (User)表实体类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:23
 */
@SuppressWarnings("serial")
@Data
public class User extends Model<User> {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别，0女，1男
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除，0未删除，1已删除
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

