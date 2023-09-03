package com.jt.service;

import com.jt.pojo.User;
import com.jt.vo.PageResult;

import java.util.List;

/**
 * @author Ming
 * 时间 2023/8/6
 */
public interface UserService {
    List<User> findAll();

    
    String findUserByUP(User user);

    PageResult findUserByPage(PageResult pageResult);

    void updateStatus(User user);

    void addUser(User user);

    void deleteUserById(Integer id);

    void updateUser(User user);
}
