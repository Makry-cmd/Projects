package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendEmail(String email, String operation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
		message.setFrom("makry32rus@yandex.ru");
        
        if ("CREATE".equals(operation)) {
            message.setSubject("Welcome!");
            message.setText("Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
        } else if ("DELETE".equals(operation)) {
            message.setSubject("Account Deleted");
            message.setText("Здравствуйте! Ваш аккаунт был удалён.");
        }
        
        mailSender.send(message);
    }
}
