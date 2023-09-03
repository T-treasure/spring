package com.zrrd.mapper;

import com.zrrd.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapperImpl implements UserMapper {
    @Override
    public void addUser(User user) {
        System.out.println("添加用户：" + user);
    }
}
