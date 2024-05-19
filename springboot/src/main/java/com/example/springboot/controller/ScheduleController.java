package com.example.springboot.controller;

import com.example.springboot.entity.Schedule;
import com.example.springboot.mapper.ScheduleMapper;
import com.example.springboot.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Schedule> findAll(){
        return scheduleMapper.findAll();
    }

    //按照id删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return scheduleMapper.deleteById(id);
    }

    //分页查询
    @GetMapping("/page")       //接口路径为/schedule/page
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String event){
        pageNum = (pageNum - 1) * pageSize;
        event = "%" + event + "%";
        List<Schedule> data = scheduleMapper.selectPage(event, pageNum, pageSize);
        Integer total = scheduleMapper.selectTotal(event);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
