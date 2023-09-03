package com.zrrd;

import com.zrrd.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class TestUser {

    @Test
    public void test1() {
        //1.通过加载配置文件 创建容器对象  IOC容器创建  内部对象也创建了
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

        //2.通过容器中获取对象  通过bean的id获取对象(通过类型获取对象)
        User user = (User) applicationContext.getBean("user");
        User user2 = applicationContext.getBean(User.class);
            user.say();
        user2.say();
    }

    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        //获取类路径
        Class<?> userClass = Class.forName("com.zrrd.pojo.User");
        //获取实例化对象
        User user = (User) userClass.newInstance();
        user.say();
    }
}
