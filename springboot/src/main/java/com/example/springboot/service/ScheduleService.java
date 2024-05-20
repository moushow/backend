package com.example.springboot.service;

import com.example.springboot.entity.Schedule;
import com.example.springboot.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public Integer deleteByIds(ArrayList<String> ids) {
        for(String id : ids){
            if(scheduleMapper.deleteById(Integer.valueOf(id)) == 1){
                continue;
            }else{
                return 0;
            }
        }
        return 1;
    }
}
