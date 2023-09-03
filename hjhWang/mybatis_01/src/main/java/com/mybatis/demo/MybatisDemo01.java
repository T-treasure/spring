package com.mybatis.demo;

//import com.mybatis.mapper.EmpMapper;
import com.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo01 {

    public static void main(String[] args) throws Exception {

        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        SqlSessionFactory build = builder.build(is);

        SqlSession sqlSession = build.openSession(false);

//        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

//        List<Emp> empList = empMapper.selectAll();
        List<Emp> empList = sqlSession.selectList("com.mybatis.mapper.EmpMapper.selectAll");

        for (Emp emp: empList) {
            System.out.println("emp = " + emp);
        }
    }

}
