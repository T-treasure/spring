package com.test;

import com.zrrd.control.UserControl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMVC {

    @Test
    public void testSpringMVC1() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        UserControl userControl = context.getBean(UserControl.class);
        userControl.addUser();
        System.out.println("恭喜你学会了！");
    }

    @Test
    public void testSpringMVC2() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("application_annotation.xml");
        UserControl userControl = (UserControl) context.getBean("userControl");
        System.out.println("userControl = " + userControl);
//        userControl.addUser();
//        System.out.println("恭喜你学会了！");
    }

}
