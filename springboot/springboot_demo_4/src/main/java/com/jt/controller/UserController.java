package com.jt.controller;

import com.jt.pojo.User;
import com.jt.serviceimpl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/userAjax")
    public String userAjax() {
        return "/userAjax";
    }

    /*
    * 接收ajax请求  /findAjaxUser
    * 返回结果 JSON
    *
    * */
    @RequestMapping("/findAjaxUser")
    @ResponseBody
    public List<User> findUser() {
        return userService.findAll();
    }

    //查找
    @RequestMapping("/userList")
    public String userList(Model model) {
        //1.业务层数据
        List<User> list = userService.findAll();
        //2.将数据保存到Model对象种返回给页面
        model.addAttribute("userList", list);
        //返回到 templates/userList.html 上
        return "userList";
    }

//    @RequestMapping("/user/{name}/{age}/{sex}")
//    public String insertUser(@PathVariable String name, @PathVariable int age, @PathVariable String sex) {
//        User user = new User();
//        user.setId(null).setName(name).setAge(age).setSex(sex);
//        userService.insert(user);
//        return "redirect:/userList";
//    }

    /*
    * 需求: RestFul风格实现用户的新增
    * 新增之后需要重定向到userList.html页面
    * URL:/user/tom/18/男
    *   优化策略:1.如果多参数提交则可以使用对象接收
    *           2.但是参数名称与属性名称一致
    * */
    @RequestMapping("/user/{name}/{age}/{sex}")
    public String insertUser(User user) {
        userService.insert(user);
        return "redirect:/userList";
    }

//    @RequestMapping("/user/{id}/{name}/{age}/{sex}")
//    public String updateUser(User user) {
//        userService.update(user);
//        return "redirect:/userList";
//    }

    /*
    * 需求: 利用RestFul风格修改数据
    *   之后重定向 userList.html页面
    * url: /user/id/name
    * */
    @RequestMapping("/user/{id}/{name}")
    private String updateUser(User user) {
        userService.update(user);
        return "redirect:/userList";
    }

    //删除
    @RequestMapping("/user/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/userList";
    }

}
