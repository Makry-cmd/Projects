package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApp {
    public static void main(String[] args) {
        
        var context = SpringApplication.run(UserServiceApp.class, args);
        
        UserCliInterface cli = context.getBean(UserCliInterface.class);
        cli.runMenu();
    }
}


   