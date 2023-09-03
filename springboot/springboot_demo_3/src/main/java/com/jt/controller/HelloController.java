package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //1.将该类交给Spring容器去管理 2.同时开启SpringMVC机制
public class HelloController {
    /*
     * 需求：http://localhost:8090/hello
     * 实现步骤：
     * 1.拦截用户请求：@RequestMapping("/hello")
     * 2.String类型的返回值 表示返回页面名称
     * 3.根据YML配置文件中的内容  动态拼接前后缀 形成页面唯一的路径
     **/
    @RequestMapping("/hello")
    public String hello() {
        //动态拼接前缀加后缀
        //classpath:/templates/hello.html
        return "hello";
    }
}
