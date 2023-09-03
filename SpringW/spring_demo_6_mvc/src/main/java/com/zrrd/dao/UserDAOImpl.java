package com.zrrd.dao;

import com.zrrd.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(User user) {
        System.out.println("添加了一名用户：" + user);
    }
}
