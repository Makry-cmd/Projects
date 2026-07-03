package com.example.controller;

import com.example.service.EmailService;
import com.example.dto.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
public class NotificationController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendManual(@RequestParam String email, @RequestParam String operation) {
        emailService.sendEmail(email, operation);
        return ResponseEntity.ok().build();
    }
}

