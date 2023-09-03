package com.zrrd;

import com.zrrd.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void test01() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        User user = context.getBean(User.class);
        System.out.println(user);
    }

    @Test
    public void test02() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        User user = (User) context.getBean("user1");
        System.out.println(user);
    }

    @Test
    public void test03() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        User user = (User) context.getBean("user1");
        System.out.println(user);
    }
    @Test
    public void test04() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        User user = (User) context.getBean("user2");
        System.out.println(user);
    }
}
