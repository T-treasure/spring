package com.zrrd.service;

import com.zrrd.dao.UserDAO;
import com.zrrd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceB")
public class UserServiceImplB implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public void addUser(User user) {
        System.out.println("实现类2完成业务逻辑");
        userDAO.addUser(user);
    }
}
