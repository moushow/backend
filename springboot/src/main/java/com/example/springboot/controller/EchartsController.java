package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private ScheduleController scheduled;

    @GetMapping("/example/{username}")
    public Result get(@PathVariable String username) {
        // 从数据库获取数据
        List<Schedule> scheduleList = scheduled.findScheduleByUserName(username);

        // 统计每一天的事件数量，并按日期排序
        Map<Date, Long> eventCountByDate = scheduleList.stream()
                .collect(Collectors.groupingBy(Schedule::getDate, TreeMap::new, Collectors.counting()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 将数据转换为前端需要的格式
        List<String> dateList = new ArrayList<>();
        List<Long> eventCountList = new ArrayList<>();

        for (Map.Entry<Date, Long> entry : eventCountByDate.entrySet()) {
            dateList.add(sdf.format(entry.getKey()));
            eventCountList.add(entry.getValue());
        }

        // 构建返回结果
        Map<String, Object> map = new HashMap<>();
        map.put("x", dateList);
        map.put("y", eventCountList);

        return Result.success(map);
    }

    @GetMapping("/members")
    public Result members() {
        List<Schedule> list = scheduled.findAll();
        List<Map<String, Object>> mapList = new ArrayList<>();
        return Result.success();
    }

}



