package com.zrrd.service;

import com.zrrd.dao.UserDAO;
import com.zrrd.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    /*
     *  关于注解的说明
     *  1.@Autowire:可以根据属性/类型注入
     *     首先按照类型注入
     *     如果类型注入失败
     *     则根据名称进行注入
     *  2.Qualifier:如果需要按照名称进行注入则需要额外店家@@Qualifier
     *  3.@Resource(type："xxx.class",name="属性名称"):
     *  会自动封装set方法 所以不需要set方法
     */
    @Resource
    private UserDAO userDAO;

    @Override
    public void addUSer(User user) {
        userDAO.addUser(user);
    }
}
