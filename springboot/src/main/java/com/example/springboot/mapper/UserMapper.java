package com.example.springboot.mapper;

import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where username = #{username}")
    User findByUserName(@Param("username") String username);

    @Insert("insert into user(username, password) values (#{username}, #{password})")
    int insert(UserDTO userDTO);

}
