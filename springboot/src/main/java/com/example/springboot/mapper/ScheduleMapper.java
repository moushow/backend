package com.example.springboot.mapper;

import com.example.springboot.entity.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    @Select("select * from schedule")
    List<Schedule> findAll();

    @Insert("insert into schedule(date, state, event, remark) values (#{date}," +
            " #{state}, #{event}, #{remark})")
    int insert(Schedule schedule);

    //这里需要动态更新，见/resources/mapper/Schedule.xml
    int update(Schedule schedule);

    @Delete("delete from schedule where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Select("select * from schedule where event like #{event} and state like #{state} and remark like #{remark} and username = #{username} limit #{pageNum}, #{pageSize}")
    List<Schedule> selectPage(@Param("event") String event, @Param("state") String state, @Param("remark") String remark, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);

    @Select("select count(*) from schedule where event like #{event} and state like #{state} and remark like #{remark} and username = #{username}")
    Integer selectTotal(@Param("event") String event, @Param("state") String state, @Param("remark") String remark, @Param("username") String username);

    @Select("SELECT * FROM schedule WHERE username = #{username} ORDER BY date ASC")
    List<Schedule> findScheduleByUserName(@Param("username") String username);
}
