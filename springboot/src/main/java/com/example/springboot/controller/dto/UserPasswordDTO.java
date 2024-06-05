package com.example.springboot.controller.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private Integer id;
    private String username;
    private String password;
    private String newPassword;
}
