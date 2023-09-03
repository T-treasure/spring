package com.zrrd.service;

import com.zrrd.dao.UserDAO;
import com.zrrd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void addUSer(User user) {
        userDAO.addUser(user);
    }
}
