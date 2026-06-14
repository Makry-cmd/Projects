package com.example.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.SimpleMailMessage;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendNotification(String email, String operation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        
        if ("CREATE".equals(operation)) {
            message.setSubject("Аккаунт создан");
            message.setText("Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
        } else if ("DELETE".equals(operation)) {
            message.setSubject("Аккаунт удален");
            message.setText("Здравствуйте! Ваш аккаунт был удалён.");
        }
        
        mailSender.send(message);
    }
}
