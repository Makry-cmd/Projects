package com.example.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter             
@Setter              
@NoArgsConstructor  
@AllArgsConstructor  
@ToString         
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
}


   
