package com.louis.rabc.module.auth.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (BlackToken)表实体类
 *
 * @author 赖小燚
 * @since 2022-09-18 00:21:06
 */
@SuppressWarnings("serial")
@Data
public class BlackToken extends Model<BlackToken> {

    /**
     * 主键
     */
    private String id;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private Date createTime;

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

