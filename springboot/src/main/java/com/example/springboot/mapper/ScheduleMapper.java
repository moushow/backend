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

    @Select("select * from schedule where event like #{event} limit #{pageNum}, #{pageSize}")
    List<Schedule> selectPage(@Param("event") String event, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from schedule where event like #{event}")
    Integer selectTotal(String event);
}
