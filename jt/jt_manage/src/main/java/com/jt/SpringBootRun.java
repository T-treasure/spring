package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Li
 * 时间 2023/8/6
 */
@SpringBootApplication
@MapperScan("com.jt.mapper")
@PropertySource(value = "classpath:properties/image.properties")
public class SpringBootRun {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootRun.class, args);
    }
}
