package com.whc.dao.mapper.secondary;

import com.whc.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by whc on 2017/4/6.
 */
@Mapper
public interface StudentSecondaryMapper {

    @Select("SELECT * FROM sbt_student WHERE NAME = #{name}")
    Student findByName(@Param("name") String name);

    @Insert("INSERT INTO sbt_student(NAME, AGE, GENDER, CREATE_TIME, UPDATE_TIME) VALUES(#{name}, #{age}, #{gender}, #{createTime}, #{updateTime})")
    int insert(Student student);

    @Delete("DELETE FROM sbt_student WHERE NAME = #{name}")
    int deleteByName(@Param("name") String name);

    @Delete("DELETE FROM sbt_student")
    int deleteAll();

    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "age", column = "AGE"),
            @Result(property = "gender", column = "GENDER"),
            @Result(property = "createTime", column = "CREATE_TIME"),
            @Result(property = "updateTime", column = "UPDATE_TIME")
    })
    @Select("SELECT ID, NAME, AGE, GENDER, CREATE_TIME, UPDATE_TIME FROM sbt_student")
    List<Student> selectAll();
}
