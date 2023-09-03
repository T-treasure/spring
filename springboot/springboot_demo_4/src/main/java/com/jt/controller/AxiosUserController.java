package com.jt.controller;

import com.jt.pojo.User;
import com.jt.serviceimpl.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class AxiosUserController {

    @Resource
    private UserService userService;

    /*
    * Axios案例
    * 1.查询数据库用户列表数据
    * 2.url: /axiosUser/findAll
    * 3.返回值结果List<User>
    * */
    @GetMapping("/axiosUser/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    /*
     * 实现入库操作
     * */
    @PostMapping("/axiosUser/insertUser")
    public void insertUser(@RequestBody User user) {
        userService.insert(user);
    }

    /*
    * 实现更新操作
    * url: http://localhost:8090/axiosUser/updateUser
    * User对象的JSON串
    * 返回值要求 void
    * */
    @PutMapping("/axiosUser/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    /*
    * 实现删除操作
    * url: http://localhost:8090/axiosUser/deleteUser?id=1
    * 参数: id=1
    * 返回值: 要求void
    * */
    @DeleteMapping("/axiosUser/deleteUser")
    public void updateUser(Integer id) {
        userService.delete(id);
    }

}
