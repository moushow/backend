package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.Schedule;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //查询所有用户
    @GetMapping
    public List<User> findUser(){
        return userMapper.findAll();
    }

    //按用户名查询
    @GetMapping("/{username}")
    public User findByUserName(@PathVariable String username){
        return userMapper.findByUserName(username);
    }

    //新增用户
    @PostMapping
    public Integer addUser(@RequestBody UserDTO userDTO){
        return userMapper.insert(userDTO);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return false;
        }
        return userService.login(userDTO);
    }

    @PostMapping("/register")
    public Integer register(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return 0;
        }
        User user = userMapper.findByUserName(userDTO.getUsername());
        if(user != null){
            return 0;
        }
        return userMapper.insert(userDTO);
    }

}

