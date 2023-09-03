package com.jt.controller;

import com.jt.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class JSONController {

    /*
    * 需求: 要根据getJSON要求请求 获取对象的JSON数据
    * 用法: 如果需要返回JSON数据则使用注解ResponseBody
    * 知识点讲解:
    *   返回对象之后: SpringMVC通过内部的API(ObjectMapper)
    *   调用对象的getxxx()方法动态的获取属性和属性值
    * 演化规则:
    *      getAge()--->去掉get字母 Age()
    *      --->首字母小写age()--->获取属性age
    *      --->通过getAge()---> 动态获取属性的值
    *   所以必须添加get/set方法
    * */
    @RequestMapping("/getJSON")
    //@ResponseBody //返回一个字符串
    public Object getJSON() {
        User user = new User();
        user.setId(100000).setName("JSON格式");
        return user;//不需要执行视图解析器
    }

}
