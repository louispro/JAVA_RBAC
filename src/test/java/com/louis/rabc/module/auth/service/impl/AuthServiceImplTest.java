package com.louis.rabc.module.auth.service.impl;

import com.louis.rabc.module.auth.entity.QQMail;
import com.louis.rabc.module.auth.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private AuthService authService;

    @Test
    void sendQQMail() {
        QQMail mail = new QQMail();
        mail.setAcceptMailAccount("1765167076@qq.com");
        mail.setTheme("测试邮件");
        mail.setMailText("加油，赖小燚！");
        this.authService.sendQQMail(mail);
    }
}