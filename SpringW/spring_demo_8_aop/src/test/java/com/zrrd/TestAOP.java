package com.zrrd;

import com.zrrd.Service.DeptService;
import com.zrrd.config.SpringConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAOP {

    @Test
    public void test01() {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringConfig.class);
        DeptService deptService = (DeptService) context.getBean("deptServiceImpl");
        deptService.addDept();
//        deptService.updateDept();
    }

    @Test
    public void test02() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        DeptService deptService = (DeptService) context.getBean("deptServiceImpl");
        String result = deptService.after(3);
        System.out.println("test测试返回通知 = " + result);
    }

    @Test
    public void test03() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        DeptService deptService = (DeptService)context.getBean("deptServiceImpl");
        deptService.afterThrow();

    }

    @Test
    public void test04() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        DeptService deptService = (DeptService) context.getBean("deptServiceImpl");
        deptService.doAround();
    }
}
