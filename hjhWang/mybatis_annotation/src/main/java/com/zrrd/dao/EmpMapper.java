package com.zrrd.dao;

import com.zrrd.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface EmpMapper {

    @Select("SELECT id, name, job from emp")
    List<Emp> queryAll() throws Exception;

    @Insert("insert into emp value (null,'暗黑梦梦','保安',3000)")
    void insert() throws Exception;

    @Delete("delete from emp where name = #{name}")
    void delete(String name) throws Exception;

    @Insert("insert into emp value (null, #{name}, #{job}, #{salary}))")
    void insert2(Map<String, Object> map) throws Exception;

    @Update("update emp set job=#{job}, salary = #{salary} where name = #{name}}")
    void update2(Emp emp) throws Exception;

}
