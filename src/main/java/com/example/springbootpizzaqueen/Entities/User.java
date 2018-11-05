package com.example.springbootpizzaqueen.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    //@OneToMany(mappedBy = "userID", cascade = CascadeType.REMOVE)
    //@JsonIgnore
    //private List<Order> orders;
}