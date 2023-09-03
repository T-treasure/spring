package com.zrrd.dao;

import com.zrrd.pojo.User;

public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) {
        System.out.println("添加用户：" + user);
    }
}
