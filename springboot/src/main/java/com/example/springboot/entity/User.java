package com.example.springboot.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String telephone;
    private String email;
    private String avatar;
}
