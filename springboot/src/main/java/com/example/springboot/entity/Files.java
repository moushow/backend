package com.example.springboot.entity;

import lombok.Data;

@Data
public class Files {
    private Integer id;
    private String name;
    private String type;
    private Long size;
    private String url;
    private String md5;
}
