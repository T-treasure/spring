package com.zrrd;

import com.zrrd.config.SpringConfig;
import com.zrrd.pojo.User;
import com.zrrd.proxy.JDKProxyFactory;
import com.zrrd.proxy.StaticProxy;
import com.zrrd.service.DeptService;
import com.zrrd.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {

    @Test
    public void testTx() {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringConfig.class);
        //如果实现类中只有一个可以用的类型的方式 多少如果是多个必须用名称
//        UserService userService = context.getBean(UserService.class);
        UserService userService = (UserService) context.getBean("userServiceImpl");
        User user = new User(1101, "无语");
        userService.addUser(user);
    }

    @Test
    public void testProxy() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = (UserService) context.getBean("userService");
        System.out.println("userService = " + userService);
        User user = new User(113, "测试代理机制");
        //执行动态代理
        userService.addUser(user);
    }


    //测试JDK动态代理
    @Test
    public void testJDKProxy() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        //1.获取目标对象
        UserService target = (UserService) context.getBean("target");
        //2.获取代理对象
        UserService userService = (UserService) JDKProxyFactory.getProxy(target);
        //3.测试是什么对象
        System.out.println(userService.getClass());
        //4.用户完成调用
        User user = new User(999, "JDK动态代理");
        userService.addUser(user);
    }

    @Test
    public void TestDeptService() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        DeptService target = (DeptService) context.getBean("deptServiceImpl");
        DeptService deptService = (DeptService) JDKProxyFactory.getProxy(target);

        System.out.println(deptService.getClass());

        deptService.addDept();
    }

}
