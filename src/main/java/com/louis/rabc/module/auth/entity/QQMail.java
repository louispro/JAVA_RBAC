package com.louis.rabc.module.auth.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * QQ邮箱
 *
 * @author louis
 * @date 2022/09/18
 */
@Getter
@Setter
public class QQMail {

    private String sendMailAccount = "1598358615@qq.com";

    private String acceptMailAccount;

    private String theme;

    private String mailText;

    private Date sendTime = new Date();

}
