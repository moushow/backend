package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
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
    public Result findByUserName(@PathVariable String username){
        User user =  userMapper.findByUserName(username);
        if(user != null){
            return Result.success(user);
        }else{
            return Result.error(Constants.CODE_401, "用户信息不存在");
        }
    }

    //新增用户
    @PostMapping
    public Integer addUser(@RequestBody UserDTO userDTO){
        return userMapper.insert(userDTO);
    }

    @PostMapping("/update")
    public Integer updateUser(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String nickname = user.getNickname();
        String telephone = user.getTelephone();
        String email = user.getEmail();
        return userMapper.update(password, nickname, telephone, email, username);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400, "参数错误");
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

