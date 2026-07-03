package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    private String email;
    private String operation;
    private long timestamp; 

    public String getFormattedEvent() {
        return String.format("Email: %s, Operation: %s, Timestamp: %d", email, operation, timestamp);
    }
}

