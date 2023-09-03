package com.zrrd.mybatis;

import com.zrrd.mybatis.dao.EmpMapper;
import com.zrrd.mybatis.dao.EmpMapperImpl;
import com.zrrd.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.*;

public class MybatisDemo03 {

    SqlSession sqlSession = null;

    @Before
    public void before() throws IOException {
        //1.读取mybatis中配置文件中配置信息(mybatis-config.xml)
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2.基于读取到的配置信息获取工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //3.打开于数据库的连接（通过工厂对象获取）
        //true 表示自动提交事务  默认false 表示关闭自动提交事务
        sqlSession = factory.openSession(false);
    }

    @Test
    public void testQueryAll() throws Exception {

        //获取EmpMapper的实现类(mybatis提供)对象的实例
//        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        EmpMapper empMapper = new EmpMapperImpl(sqlSession);

        List<Emp> empList = empMapper.queryAll();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
        sqlSession.close();

    }

    @Test
    public void testInsert() throws Exception {

        //获取EmpMapper的实现类(mybatis提供)对象的实例
//        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        EmpMapper empMapper = new EmpMapperImpl(sqlSession);

        empMapper.insert();

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testDelete() throws Exception {

        //获取EmpMapper的实现类(mybatis提供)对象的实例
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
//        EmpMapper empMapper = new EmpMapperImpl(sqlSession);

        empMapper.delete("炼金梦梦");

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testInsert2() throws Exception{
        //获取EmpMapper的实现类(mybatis提供)对象的实例
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "王者梦梦");
        map.put("job","保安");
        map.put("salary", 3000);
        empMapper.insert2(map);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate2() throws Exception{
        //获取EmpMapper的实现类(mybatis提供)对象的实例
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

        Emp emp = new Emp();
        emp.setName("王者梦梦");
        emp.setJob("程序员");
        emp.setSalary(3200.0);

        empMapper.update2(emp);

        sqlSession.commit();
        sqlSession.close();
    }



}
