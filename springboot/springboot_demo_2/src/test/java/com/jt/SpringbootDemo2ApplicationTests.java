package com.jt;

import com.jt.mapper.DemoUserMapper;
import com.jt.pojo.DemoUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootDemo2ApplicationTests {

    @Resource
    private DemoUserMapper userMapper;
    @Test
    void contextLoads() {
        List<DemoUser> userList = userMapper.findAll();
        for (DemoUser user : userList) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testInsert() {
        DemoUser user = new DemoUser();
        user.setName("大罗金仙").setAge(25).setSex("男");
        userMapper.insertUser(user);
    }

    @Test
    public void testUpdate() {
        DemoUser user = new DemoUser();
        user.setName("大罗金仙").setAge(35).setSex("仙人");
        userMapper.updateUser(user);
    }

    @Test
    public void testDelete() {
        userMapper.deleteUser("大罗金仙");
    }

    @Test
    public void test01() {
        List<DemoUser> userList = userMapper.selectList(null);
        for (DemoUser user : userList) {
            System.out.println(user);
        }
    }
}
