package com.louis.rabc.module.auth.service;

import cn.hutool.extra.mail.Mail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.auth.entity.QQMail;

/**
 * (Keys)表服务接口
 *
 * @author 赖小燚
 * @since 2022-09-16 12:22:40
 */
public interface AuthService extends IService<Auth> {

    void sendQQMail(QQMail mail);
}

