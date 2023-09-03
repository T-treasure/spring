package com.zrrd.config;

import com.zrrd.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//包扫描
@ComponentScan("com.zrrd")
//@PropertySource作用:加载指定的properties配置文件 将数据保存在Spring容器中
//encoding: 指定字符集编码格式
@PropertySource(value = "classpath:/user.properties", encoding = "UTF-8")
public class SpringConfig {


    //定义对象属性，准备接收数据
    //@Value(123) 将数字直接写在()会报错
    //@Value("${user.id}") 在Spring容器中查找key=user.id的数据 通过${}进行触发
    @Value("${user.id}")
    private Integer id;
    @Value("${user.username}")
    private String username;

    /*
     * 1.Spring配置文件写法<bean id="方法名称" class="返回值类型"/>
     * 2.执行@Bean方法 将名称当做ID  返回值的对象当做Value 直接保存到Map集合
     */
    @Bean
    public User user(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }

}
