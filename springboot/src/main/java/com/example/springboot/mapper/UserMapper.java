package com.example.springboot.mapper;

import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.controller.dto.UserPasswordDTO;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where username = #{username}")
    User findByUserName(@Param("username") String username);

    @Insert("insert into user(username, password) values (#{username}, #{password})")
    int insert(UserDTO userDTO);

    @Update("update user set password = #{password}, nickname = #{nickname}, telephone = #{telephone}, email = #{email}, avatar = #{avatar} where username = #{username}")
    Integer update(@Param("password") String password, @Param("nickname") String nickname, @Param("telephone") String telephone, @Param("email") String email, @Param("avatar") String avatar, @Param("username") String username);

    @Update("update user set password = #{newPassword} where username = #{username} and password = #{password}")
    Integer updatePassword(UserPasswordDTO userPasswordDTO);
}
