package com.example.springboot.mapper;

import com.example.springboot.entity.Curriculum;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CurriculumMapper {
    @Select("select * from curriculum")
    List<Curriculum> findAll();

    @Insert("insert into curriculum(name, no, teacher, room, week_day, section, username) values (#{name}," +
            " #{no}, #{teacher}, #{room}, #{week_day}, #{section}, #{username})")
    int insert(Curriculum curriculum);

    //这里需要动态更新，见/resources/mapper/Curriculum.xml
    int update(Curriculum curriculum);

    @Delete("delete from curriculum where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Select("select * from curriculum where name like #{name} and teacher like #{teacher} and no like #{no} and username = #{username} limit #{pageNum}, #{pageSize}")
    List<Curriculum> selectPage(@Param("name") String name, @Param("teacher") String teacher, @Param("no") String no, @Param("username") String username, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from curriculum where name like #{name} and teacher like #{teacher} and no like #{no} and username = #{username}")
    Integer selectTotal(@Param("name") String name, @Param("teacher") String teacher, @Param("no") String no, @Param("username") String username);

    @Select("SELECT * FROM curriculum WHERE username = #{username}")
    List<Curriculum> findCurriculumByUserName(@Param("username") String username);
}
