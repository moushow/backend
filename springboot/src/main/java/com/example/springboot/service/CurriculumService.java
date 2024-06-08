package com.example.springboot.service;

import com.example.springboot.entity.Curriculum;
import com.example.springboot.mapper.CurriculumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CurriculumService {

    @Autowired
    private CurriculumMapper curriculumMapper;

    public int save(Curriculum curriculum){
        if(curriculum.getId() == null){      //没有id是新增，有是更新
            return curriculumMapper.insert(curriculum);
        }else{
            return curriculumMapper.update(curriculum);
        }
    }

    public Integer deleteByIds(ArrayList<String> ids) {
        for(String id : ids){
            if(curriculumMapper.deleteById(Integer.valueOf(id)) == 1){
                continue;
            }else{
                return 0;
            }
        }
        return 1;
    }
}
