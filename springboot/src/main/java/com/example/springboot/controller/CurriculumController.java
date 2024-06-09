package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Curriculum;
import com.example.springboot.mapper.CurriculumMapper;
import com.example.springboot.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Autowired
    private CurriculumService curriculumService;

    @PostMapping
    public Integer save(@RequestBody Curriculum curriculum){
        //新增或修改
        return curriculumService.save(curriculum);
    }

    //查询所有课程
    @GetMapping
    public List<Curriculum> findAll(){
        return curriculumMapper.findAll();
    }

    //按照username查询课程
    @GetMapping("/{username}")
    public List<Curriculum> findCurriculumByUserName(@PathVariable String username){
        return curriculumMapper.findCurriculumByUserName(username);
    }

    private void setJson(String week_day, Curriculum curriculum, JSONObject jsonObject){
        if("1".equals(week_day)){
            jsonObject.set("mon", curriculum);
        }else if("2".equals(week_day)){
            jsonObject.set("tue", curriculum);
        }else if("3".equals(week_day)){
            jsonObject.set("wes", curriculum);
        }else if("4".equals(week_day)){
            jsonObject.set("thu", curriculum);
        }else if("5".equals(week_day)) {
            jsonObject.set("fri", curriculum);
        }else if("6".equals(week_day)){
            jsonObject.set("sat", curriculum);
        }else if("7".equals(week_day)){
            jsonObject.set("sun", curriculum);
        }
    }

    //查询课表
    @GetMapping("/courseTable/{username}")
    public Result findCourseTable(@PathVariable String username){
        List<Curriculum> Curriculums = findCurriculumByUserName(username);
        List<JSONObject> list = CollUtil.newArrayList();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        JSONObject jsonObject5 = new JSONObject();
        jsonObject1.set("section", JSONUtil.parseObj("{\"num\": \"第一大节\", \"time\": \"08:00-09:35\"}"));
        jsonObject2.set("section", JSONUtil.parseObj("{\"num\": \"第二大节\", \"time\": \"10:00-12:25\"}"));
        jsonObject3.set("section", JSONUtil.parseObj("{\"num\": \"第三大节\", \"time\": \"13:25-15:50\"}"));
        jsonObject4.set("section", JSONUtil.parseObj("{\"num\": \"第四大节\", \"time\": \"16:15-17:50\"}"));
        jsonObject5.set("section", JSONUtil.parseObj("{\"num\": \"第五大节\", \"time\": \"18:50-21:15\"}"));
        Curriculums.forEach(curriculum -> {
            String week_day = curriculum.getWeek_day();
            String section = curriculum.getSection();
            switch(section){
                case "1":
                    setJson(week_day, curriculum, jsonObject1);
                    break;
                case "2":
                    setJson(week_day, curriculum, jsonObject2);
                    break;
                case "3":
                    setJson(week_day, curriculum, jsonObject3);
                    break;
                case "4":
                    setJson(week_day, curriculum, jsonObject4);
                    break;
                case "5":
                    setJson(week_day, curriculum, jsonObject5);
                    break;
            }
        });
        list.add(jsonObject1);
        list.add(jsonObject2);
        list.add(jsonObject3);
        list.add(jsonObject4);
        list.add(jsonObject5);
        return Result.success(list);
    }

    //按照id删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return curriculumMapper.deleteById(id);
    }

    //批量删除数据
    @PostMapping ("/del/batch")
    public Integer deleteBatch(@RequestBody ArrayList<String> ids){
        return curriculumService.deleteByIds(ids);
    }

    //分页查询
    @GetMapping("/page")       //接口路径为/curriculum/page
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name,
                                        @RequestParam String teacher,
                                        @RequestParam String no,
                                        @RequestParam String username){
        pageNum = (pageNum - 1) * pageSize;
        name = "%" + name + "%";
        teacher = "%" + teacher + "%";
        no = "%" + no + "%";
        List<Curriculum> data = curriculumMapper.selectPage(name, teacher, no, username, pageNum, pageSize);
        Integer total = curriculumMapper.selectTotal(name, teacher, no, username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
