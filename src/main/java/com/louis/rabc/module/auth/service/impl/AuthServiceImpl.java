package com.louis.rabc.module.auth.service.impl;

import cn.hutool.extra.mail.Mail;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.annotation.AuthAndResponseUnify;
import com.louis.rabc.module.auth.dao.AuthDao;
import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.auth.entity.QQMail;
import com.louis.rabc.module.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * (Keys)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-16 12:22:40
 */
@Service("keysService")
@AllArgsConstructor
public class AuthServiceImpl extends ServiceImpl<AuthDao, Auth> implements AuthService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendQQMail(QQMail mail) {
        //构建邮件内容对象
        SimpleMailMessage msg = new SimpleMailMessage();
        //邮件发送者
        msg.setFrom(mail.getSendMailAccount());
        //邮件接收者
        msg.setTo(mail.getAcceptMailAccount());
        //邮件主题
        msg.setSubject(mail.getTheme());
        //邮件正文
        msg.setText(mail.getMailText());
        //邮件发送时间
        msg.setSentDate(mail.getSendTime());
        //邮件发送
        javaMailSender.send(msg);
    }
}

