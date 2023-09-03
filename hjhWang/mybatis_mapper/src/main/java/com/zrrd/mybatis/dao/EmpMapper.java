package com.zrrd.mybatis.dao;

import com.zrrd.mybatis.pojo.Emp;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
    List<Emp> queryAll() throws Exception;

    void insert() throws Exception;

    void delete(String name) throws Exception;

    void update(List<Integer> list) throws Exception;

    void insert2(Map<String, Object> map) throws Exception;

    void update2(Emp emp) throws Exception;

}
