package com.example.springbootpizzaqueen.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userId;

    private String userName;

    private String password;

    private String realName;

    private String address;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_GUEST, ROLE_USER, ROLE_ADMIN
    }

}