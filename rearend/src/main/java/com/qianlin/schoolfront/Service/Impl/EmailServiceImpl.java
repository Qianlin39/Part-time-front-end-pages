package com.qianlin.schoolfront.Service.Impl;

import com.qianlin.schoolfront.Mapper.EmailMapper;
import com.qianlin.schoolfront.Pojo.User;
import com.qianlin.schoolfront.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public User sendEmail(String email) {
        if (emailMapper.findByEmail(email) != null) {
            return emailMapper.findByEmail(email);
        }
        return null;
    }

    @Override
    public String sEmail(String useremail) {
        // 生成随机验证码
        String code = generateCode();
        // 发送邮件
        sendEmail(useremail, code);
        // 发送邮件的代码
        return code;
    }

    private void sendEmail(String useremail, String code) {
        // 发送邮件的代码
        SimpleMailMessage mes = new SimpleMailMessage();
        mes.setFrom("gpy203129@163.com");
        mes.setTo(useremail);
        mes.setSubject("验证码");
        mes.setText("您的验证码是：" + code);
        // 发送邮件
        mailSender.send(mes);


    }

    // 生成随机验证码
    public String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10)); // 生成 0-9 的随机数
        }
        return code.toString();
    }
}
