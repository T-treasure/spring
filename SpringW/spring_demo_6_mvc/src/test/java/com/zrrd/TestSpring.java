package com.zrrd;

import com.zrrd.config.SpringConfig;
import com.zrrd.controller.UserController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void test01() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("application_annotation.xml");
        UserController controller = context.getBean(UserController.class);
        controller.addUser();
    }

    @Test
    public void test02() {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController controller = context.getBean(UserController.class);
        controller.addUser();
    }

}
