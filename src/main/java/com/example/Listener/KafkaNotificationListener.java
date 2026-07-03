package com.example.listener;

import com.example.dto.UserEvent;
import com.example.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service 
@RequiredArgsConstructor
@Slf4j
public class KafkaNotificationListener {

    private final EmailService emailService;

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(UserEvent event) {
        log.info("Received event: {}", event);
        
        emailService.sendEmail(event.getEmail(), event.getOperation());
    }
}


