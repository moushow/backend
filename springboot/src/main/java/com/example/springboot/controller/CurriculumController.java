package com.example.springboot.controller;

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
