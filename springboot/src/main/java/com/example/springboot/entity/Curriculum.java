package com.example.springboot.entity;

import lombok.Data;

@Data
public class Curriculum {
    private Integer id;
    private String name;
    private String no;
    private String teacher;
    private String room;
    private String week_day;
    private String section;
    private String username;
}
