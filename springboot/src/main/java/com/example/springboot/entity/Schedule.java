package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Schedule {
    private Integer id;
    private String state;
    private String remark;
    private String event;
    private Date date;
}
