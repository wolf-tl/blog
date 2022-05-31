package com.tl.blog.service;

import com.tl.blog.utils.RandomCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author tl
 */
@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public String sendMail(String mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置主题
        mailMessage.setSubject("发送邮箱验证码");
        // 验证码
        String code = RandomCodeUtils.get4BitRandom();
        // 设置邮件内容
        mailMessage.setText("验证码(5分钟后过期)："+code);
        // 设置收发人
        mailMessage.setFrom(from);
        mailMessage.setTo(mail);
        // 发送邮件
        mailSender.send(mailMessage);
        // 将该验证码传给控制层
        return code;
    }
}
