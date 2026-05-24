   package com.example.model;

   import javax.persistence.*;
   import java.time.LocalDateTime;

public class User {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    // Конструктор
    public User(Long id, String username, String email, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }

    // Геттер для id
    public Long getId() {
        return id;
    }

    // Сеттер для id
    public void setId(Long id) {
        this.id = id;
    }

    // Геттер для username
    public String getUsername() {
        return username;
    }

    // Сеттер для username
    public void setUsername(String username) {
        this.username = username;
    }

    // Геттер для email
    public String getEmail() {
        return email;
    }

    // Сеттер для email
    public void setEmail(String email) {
        this.email = email;
    }

    // Геттер для createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Сеттер для createdAt
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

   
