package com.example.springboot.controller;

import com.example.springboot.entity.Schedule;
import com.example.springboot.mapper.ScheduleMapper;
import com.example.springboot.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public Integer save(@RequestBody Schedule schedule){
        //新增或修改
        return scheduleService.save(schedule);
    }

    //查询所有数据
    @GetMapping
    public List<Schedule> findSchedule(){
        return scheduleMapper.findAll();
    }

    //按照id删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return scheduleMapper.deleteById(id);
    }
}
