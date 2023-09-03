package com.zrrd.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.zrrd")
//启用AOP注解创建代理对象
//默认情况下启用JDK动态代理
//目标对象没有实现接口时，才使用CGLIB
//强制使用CGLIB  proxyTargetClass = true
//JDK代理创建速度较快，运行时较慢
//CGLIB代理创建速度较慢，运行时较快
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class SpringConfig {



}
