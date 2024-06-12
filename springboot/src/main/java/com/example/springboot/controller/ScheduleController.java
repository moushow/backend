package com.example.springboot.controller;

import com.example.springboot.entity.Schedule;
import com.example.springboot.mapper.ScheduleMapper;
import com.example.springboot.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    //查询所有日程
    @GetMapping
    public List<Schedule> findAll(){
        return scheduleMapper.findAll();
    }

    //按照username查询日程
//    @GetMapping("/{username}")
//    public List<Schedule> findScheduleByUserName(@PathVariable String username){
//        return scheduleMapper.findScheduleByUserName(username);
//    }
    //按照username查询日程并升序
    @GetMapping("/{username}")
    public List<Schedule> findScheduleByUserName(@PathVariable String username){
        return scheduleMapper.findScheduleByUserName(username).stream()
                .sorted(Comparator.comparing(Schedule::getDate))
                .collect(Collectors.toList());
    }

    @GetMapping("/events/{username}")
    public List<Map<String, String>> findEventsByUserName(@PathVariable String username) {
        // 获取今天的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        return scheduleMapper.findScheduleByUserName(username).stream()
                .filter(schedule -> today.equals(sdf.format(schedule.getDate())))
                .sorted(Comparator.comparing(Schedule::getDate))
                .map(schedule -> {
                    Map<String, String> eventMap = new HashMap<>();
                    eventMap.put("event", schedule.getEvent());
                    return eventMap;
                })
                .collect(Collectors.toList());
    }

    //按照id删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return scheduleMapper.deleteById(id);
    }

    //批量删除数据
    @PostMapping ("/del/batch")
    public Integer deleteBatch(@RequestBody ArrayList<String> ids){
        return scheduleService.deleteByIds(ids);
    }

    //分页查询
    @GetMapping("/page")       //接口路径为/schedule/page
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username,
                                        @RequestParam String event,
                                        @RequestParam String state,
                                        @RequestParam String remark){
        pageNum = (pageNum - 1) * pageSize;
        event = "%" + event + "%";
        state = "%" + state + "%";
        remark = "%" + remark + "%";
        List<Schedule> data = scheduleMapper.selectPage(event, state, remark, pageNum, pageSize, username);
        Integer total = scheduleMapper.selectTotal(event, state, remark, username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
