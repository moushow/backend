package com.example.springboot.mapper;

import com.example.springboot.entity.Files;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO file (name, type, size, url, md5) VALUES (#{name}, #{type}, #{size}, #{url}, #{md5})")
    void insert(Files file);

    @Select("SELECT * FROM file WHERE md5 = #{md5}")
    Files getFileByMD5(@Param("md5") String md5);

    @Select("SELECT * FROM file WHERE id = #{id}")
    Files selectById(@Param("id") Integer id);

    @Update("UPDATE file SET name = #{name}, type = #{type}, size = #{size}, url = #{url}, md5 = #{md5} WHERE id = #{id}")
    int updateById(Files file);

    @Delete("DELETE FROM file WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);

}
