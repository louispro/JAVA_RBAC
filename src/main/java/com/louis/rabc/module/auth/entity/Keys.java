package com.louis.rabc.module.auth.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;

/**
 * (Keys)表实体类
 *
 * @author 赖小燚
 * @since 2022-09-16 12:22:40
 */
@SuppressWarnings("serial")
@Data
public class Keys extends Model<Keys> {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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

