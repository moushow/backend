package com.example.springboot.mapper;

import com.example.springboot.entity.Friend;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendMapper {
    @Insert("insert into friend(username, friendName, state) values (#{username}, #{friendName}, '待确认')")
    Integer application(Friend friend);


    @Select("SELECT * FROM friend WHERE friendName = #{friendName} AND username = #{username}")
    Friend findFriend(@Param("friendName") String friendName, @Param("username") String username);

    @Delete("DELETE FROM friend WHERE (username = #{username} AND friendName = #{friendName}) OR (username = #{friendName} AND friendName = #{username})")
    Integer deleteByFriendName(@Param("friendName") String friendName, @Param("username") String username);


    @Select("select * from friend where friendName like #{friendName} and username = #{username} and state = '好友' limit #{pageNum}, #{pageSize}")
    List<Friend> selectPage(@Param("friendName") String friendName, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);

    @Select("select count(*) from friend where friendName like #{friendName} and username = #{username} and state = '好友'")
    Integer selectTotal(@Param("friendName") String friendName, @Param("username") String username);

    @Select("SELECT * FROM friend WHERE friendName = #{username} AND state = '待确认'")
    Friend findApplication(String friendName);

    @Update("update friend set state = '好友', nickname = #{nickname}, telephone = #{telephone}, email = #{email} where username = #{friendName} and friendName = #{username}")
    int agree(@Param("username") String username, @Param("friendName") String friendName, @Param("nickname") String nickname, @Param("telephone") String telephone, @Param("email") String email);

    @Delete("delete from friend where friendName = #{username} and username = #{friendName} and state = '待确认'")
    int refuse(@Param("username") String username, @Param("friendName") String friendName);

    @Insert("insert into friend(username, friendName, state, nickname, telephone, email) values (#{username}, #{friendName}, '好友', #{nickname}, #{telephone}, #{email})")
    int insert(@Param("username") String username, @Param("friendName") String friendName, @Param("nickname") String nickname, @Param("telephone") String telephone, @Param("email") String email);
}
