package com.zrrd.service;

import com.zrrd.mapper.UserMapper;
import com.zrrd.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("target")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

//    @Override
//    public void addUser(User user) {
//        try{
//            System.out.println("事务开启");
//            userMapper.addUser(user);
//            System.out.println("事务结束");
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("事务回滚");
//        }
//        userMapper.addUser(user);
//    }

}
