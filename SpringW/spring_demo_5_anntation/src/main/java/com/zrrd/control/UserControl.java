package com.zrrd.control;

import com.zrrd.pojo.User;
import com.zrrd.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserControl {

    //Spring容器负责userService赋值
    private UserService userService;
    private User user;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser() {
        userService.addUSer(user);
    }

}
