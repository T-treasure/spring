package com.zrrd.demo;

import com.zrrd.dao.EmpMapper;
import com.zrrd.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo04 {

    SqlSession sqlSession = null;

    @Before
    public void testBefore() throws IOException {
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
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

        List<Emp> empList = empMapper.queryAll();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
        sqlSession.close();

    }

}
