package com.example.springboot.service;

import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.Schedule;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        // 查找用户
        User user = userMapper.findByUserName(username);
        if (user == null || !user.getPassword().equals(password)) {
            return Result.error(Constants.CODE_401, "用户名或密码错误");
        }
        if(user.getNickname() != null){
            userDTO.setNickname(user.getNickname());
        }else{
            userDTO.setNickname(user.getUsername());
        }
        return Result.success(userDTO);
    }
}
