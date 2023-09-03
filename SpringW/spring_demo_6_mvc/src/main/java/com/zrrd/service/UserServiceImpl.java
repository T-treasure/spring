package com.zrrd.service;

import com.zrrd.dao.UserDAO;
import com.zrrd.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userServiceA")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }
}
