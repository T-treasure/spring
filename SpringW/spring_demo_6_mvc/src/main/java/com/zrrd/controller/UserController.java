package com.zrrd.controller;

import com.zrrd.pojo.User;
import com.zrrd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {

//    @Autowired
//    @Qualifier("userServiceB")
    @Resource(name = "userServiceA")
    private UserService userService;

    @Resource
    private User user;

    public void addUser() {
//        User user = new User();
//        user.setId(101);
//        user.setUsername("无语子");
//        userService.addUser(user);
        userService.addUser(user);
    }
}
