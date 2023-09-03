package com.mybatis.demo;

import com.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Li
 */
public class MybatisDemo02 {
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
    public void selectAll() {
        List<Emp> empList = sqlSession.selectList("com.mybatis.mapper.EmpMapper.selectAll");
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void testInsert(){
        int rows = sqlSession.insert("com.mybatis.mapper.EmpMapper.insert");
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }

    @Test
    public void testUpdate(){
        int rows = sqlSession.insert("com.mybatis.mapper.EmpMapper.update");
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }

    @Test
    public void testDelete(){
        int rows = sqlSession.insert("com.mybatis.mapper.EmpMapper.delete");
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }

    @Test
    public void testInsert2() {
//        Emp emp = new Emp();
//        emp.setName("马老师");
//        emp.setJob("金牌讲师");
//        emp.setSalary(3000.0);
//        int rows = sqlSession.insert("com.mybatis.mapper.EmpMapper.insert2", emp);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "马老师");
        map.put("job", "金牌厨师");
        map.put("salary", 3000);
        int rows = sqlSession.insert("com.mybatis.mapper.EmpMapper.insert2", map);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }

    @Test
    public void testUpdate2() {
        Emp emp1 = new Emp(null, "马老师", "金牌讲师", 6000.0);
//        Emp emp = new Emp();
//        emp.setName("马老师");
//        emp.setJob("职业选手");
//        emp.setSalary(5000.0);
        int rows = sqlSession.update("com.mybatis.mapper.EmpMapper.update2", emp1);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }

    @Test
    public void testSelect2() {
        Map<String, Object> map = new HashMap<>();
        map.put("columns", "name, job");
        List<Emp> empList = sqlSession.selectList("com.mybatis.mapper.EmpMapper.select2", map);
        for (Emp emp : empList) {
            System.out.println(emp);
        }
        sqlSession.close();
    }

    @Test
    public void testDelete2() {
        Map<String, Object> map = new HashMap<>();
        map.put("where", "name = '马老师' and id = 15");
        int rows = sqlSession.delete("com.mybatis.mapper.EmpMapper.delete2", map);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }

    @Test
    public void testDelete3() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "马老师");
        int rows = sqlSession.delete("com.mybatis.mapper.EmpMapper.delete3", map);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }
    @Test
    public void testSelect3() {
        Map<String, Object> map = new HashMap<>();
        map.put("minSal", 3000);
        map.put("maxSal", 4000);
        List<Emp> empList = sqlSession.selectList("com.mybatis.mapper.EmpMapper.select3", map);
        sqlSession.close();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void testSelect4() {
        Map<String, Object> map = new HashMap<>();
//        map.put("minSal", 3000);
        map.put("maxSal", 4000);
        List<Emp> empList = sqlSession.selectList("com.mybatis.mapper.EmpMapper.select4", map);
        sqlSession.close();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void testDelete4() {
        Map<String, Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1,4,7,9);
        map.put("ids",list);
        int rows = sqlSession.delete("com.mybatis.mapper.EmpMapper.delete4", map);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }

    @Test
    public void testUpdate3() {
        Map<String, Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 2,3,6,8);
        map.put("ids",list);
        int rows = sqlSession.update("com.mybatis.mapper.EmpMapper.update3", map);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("rows = " + rows);
    }

}
