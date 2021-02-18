package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String email, String code, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("stacvixndrumem@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(code);
        mailSender.send(message);
    }
}
