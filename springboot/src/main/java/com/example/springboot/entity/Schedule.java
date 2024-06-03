package com.example.springboot.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Schedule {
    private Integer id;
    private String state;
    private String remark;
    private String event;
    private Date date;
    private String username;
}
