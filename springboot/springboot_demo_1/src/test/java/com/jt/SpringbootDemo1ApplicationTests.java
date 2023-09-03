package com.jt;

import com.jt.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//在执行测试方法的时候自动启动spring容器
@SpringBootTest
class SpringbootDemo1ApplicationTests {

    @Resource
    private Dept dept;


    @Test
    void contextLoads() {
        System.out.println(dept);
    }



}
