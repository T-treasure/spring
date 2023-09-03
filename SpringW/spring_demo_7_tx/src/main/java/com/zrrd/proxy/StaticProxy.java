package com.zrrd.proxy;

import com.zrrd.pojo.User;
import com.zrrd.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class StaticProxy implements UserService{

    @Resource
    private UserService target;

    //目的: 对原有的对象进行扩展
    public void addUser(User user) {
        try {
            System.out.println("事务开始！");
            target.addUser(user);
            System.out.println("事务结束！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("事务回滚！");
        }
    }

}
