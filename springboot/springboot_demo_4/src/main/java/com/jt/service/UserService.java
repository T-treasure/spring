package com.jt.serviceimpl;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void insert(User user);

    void update(User user);
    void delete(int id);
}
