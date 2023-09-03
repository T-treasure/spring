package com.jt.controller;

import com.jt.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class UserController {

    /*
     * 常见的请求
     * get请求:
     * url地址: http://localhost:8090/restFul?id=1&name=Tom
     * get请求的弊端: 如果参数有多个 则 key-value的结构需要多份
     * RestFul结构:
     *   url地址:http://localhost:8090/restFul/{id}/{name}
     *   1.参数之间以斜杆分割
     *   2.参数位置一旦确定不可更改
     *   3.参数使用{使用{}的形式包裹 并且设定形参
     *   4.在接收参数时 使用特定的注解取值
     * @PathVariable: 参数说明
     *       1.name/value 动态接收形参的数据  如果参数相同可以不写
     *       2.必填项  required  默认true
     * */
    //@RequestMapping(value = "/restFul/{id}/{name}" , method = RequestMethod.GET)
    //@RequestMapping(value = "/restFul/{id}/{name}" , method = RequestMethod.POST)
    //@RequestMapping(value = "/restFul/{id}/{name}" , method = RequestMethod.PUT)
    //@RequestMapping(value = "/restFul/{id}/{name}" , method = RequestMethod.DELETE)
    //@RequestMapping("/restFul/{id}/{name}")   //默认支持所有请求
    @GetMapping("/restFul/{id}/{name}")
    public String restFul(@PathVariable Integer id, @PathVariable String name) {
        System.out.println("获取参数: " + id + " | " + name);
        return "success";
    }

    /*
     * 测试转发和重定向
     * 1.准备一个请求findUser
     * 2.要求转发到findDog请求中
     * 3.关键字
     *         forward
     *         redirect
     * 4.特点:
     *        1.转发时会携带用户提交的数据
     *        2.转发时用户浏览器的地址不会改变
     *        3.重定向时 由于多次请求  所以不会携带用户数据
     *        4.重定向时 由于多次请求  所以用户的浏览器地址会发生改变
     * */
    @RequestMapping("/findUser")
    public String findUser(String name) {
        //return  本身就是一个转发
        //return "user1"
        //return "dog" //页面耦合性太高不符合业务逻辑性
        //return "forward:/findDog";//转发到findDog请求
        return "redirect:/findDog"; //重定向findDog请求
    }

    @RequestMapping("/findDog")
    public String findDog(String name, Model model) {
        System.out.println("动态获取的属性:" + name);
        model.addAttribute("name", name + "旺旺旺");
        return "dog";
    }

    /*
     * 引用赋值
     * */
//    @RequestMapping("/addUser")
    public String addUserDog(User user) {
        System.out.println(user);
        return "success";
    }


    /*
     * 使用对象的方式接收数据
     * url参数 id : name : hobbies
     * 对象赋值原理:
     * 要求: pojo对象中必须有get/set方法
     * 当用户提交数据之后 利用对象的set方法赋值
     * */
//    @RequestMapping("/addUser")
    public String addUser3(User user) {
        System.out.println(user);
        return "success";
    }

    /*
     * 同名提交测
     * url参数 id:name:hobbies
     * 参数提交形式:SpringMVC自动将参数进行了","拼接  敲键盘, 敲键盘, 敲键盘
     * SpringMVC优化
     *   1.可以根据","自动将字符串进行拆分
     *   2.如果数据不是String类型  则可以自动转化
     *   总结: 如果以后遇到同名提交问题  则使用数组 或者可变参数进行接收(可变参数必须在最后)
     * */
//    @RequestMapping("/addUser")
    public String addHobbies(Integer id, String name, String... hobbies) {
        System.out.println("参数获取:" + id + " : " + name + " : " + Arrays.toString(hobbies));
        return "success";
    }

    /*
     * 请求参数: id:100 name:张三
     * 测试@RequestParam
     * @RequestParam 参数说明
     * 1.name/value 接收参数的名称
     * 2.require 默认值为true 该数据项为必填项
     * 3.defaultValue 设定数据默认值  如果参数为null则设定默认值
     * required/defaultValue 是互斥的
     * */
//    @RequestMapping("/addUser")
    public String addUserParam(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
                               @RequestParam(value = "name", required = false, defaultValue = "张三") String name) {
        System.out.println("参数: " + id + " : " + name);
        return "success";
    }

    /*
     * 请求路径: http://localhost:8090/addUser
     * 请求参数: id: 123 name: 张三
     * servlet缺点: 接收参数都是String类型
     * */
//    @RequestMapping("/addUser")
    public String addUser2(Integer id, String name) {
        System.out.println("参数: " + id + " : " + name);
        return "success";
    }

    //    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        System.out.println("参数: " + id + " : " + name);
        return "success";
    }

    //简单数据传递
    @RequestMapping("/user")
    public String toUser2(Model model) {
        //将数据通过model进行传递
        model.addAttribute("id", 1003);
        model.addAttribute("name", "战双");
        return "user";
    }

    /*
     * mvc底层数据传输原则
     * url:http//localhost:8090/user
     * ModelAndView:
     *    1.Model  封装数据
     *    2.View   封装视图页面
     * */
//    @RequestMapping("/user")
    public ModelAndView toUser() {
        ModelAndView modelAndView = new ModelAndView();
        //封装数据
        modelAndView.addObject("id", 1001);
        modelAndView.addObject("name", "甘雨");
        //封装页面数据
        modelAndView.setViewName("user");
        return modelAndView;
    }

}
