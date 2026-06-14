package com.example.controller;

import com.example.service.EmailService;
import org.springframework.kafka.core.KafkaTemplate;
import com.example.dto.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.kafka.annotation.KafkaListener;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final EmailService emailService;

   
    @PostMapping("/api/notify")
    public void sendManual(@RequestParam String email, @RequestParam String operation) {
        emailService.sendNotification(email, operation);
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(UserEvent event) {
        emailService.sendNotification(event.email(), event.operation());
    }
}
