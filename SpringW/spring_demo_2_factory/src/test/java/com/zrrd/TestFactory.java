package com.zrrd;

import com.zrrd.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

public class TestFactory {

    @Test
    public void testStatic() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        Calendar calendar = (Calendar) context.getBean("calendar1");
        System.out.println("获取当前时间:" + calendar.getTime());
    }

    @Test
    public void testInstance() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        Calendar calendar = (Calendar) context.getBean("calendar2");
        System.out.println("获取当前时间:" + calendar.getTime());
    }

    @Test
    public void testSpringFactory() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        Calendar calendar = (Calendar) context.getBean("calendar3");
        System.out.println("获取当前时间：" + calendar.getTime());
    }

    @Test
    public void testUser(){
        ApplicationContext context
                = new ClassPathXmlApplicationContext("application.xml");
        User user1 = (User) context.getBean("user");
        User user2 = (User) context.getBean("user");
        User user3 = (User) context.getBean("user");
    }
    @Test
    public void testLife(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        //获取对象
        User user = context.getBean(User.class);
        //用户调用方法
        user.say();
        //只要容器关闭，对象销毁
        context.close();
    }
}
