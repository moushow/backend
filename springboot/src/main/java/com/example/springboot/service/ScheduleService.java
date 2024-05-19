package com.example.springboot.service;

import com.example.springboot.entity.Schedule;
import com.example.springboot.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    public int save(Schedule schedule){
        if(schedule.getId() == null){      //没有id是新增，有是更新
            return scheduleMapper.insert(schedule);
        }else{
            return scheduleMapper.update(schedule);
        }
    }
}
