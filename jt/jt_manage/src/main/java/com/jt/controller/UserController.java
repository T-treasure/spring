package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li
 * 时间 2023/8/6
 */
@RestController
@CrossOrigin
@RequestMapping("/user")    //抽取公共的请求
public class UserController {

    @Autowired
    private UserService userService;

    /*
    * 1.url地址: /user/login
    * 2.请求参数 用户表单对象的JSON串 post类型
    * 3.返回值结果 SysResult  token ? 有值  正确 null错误
    * */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user) {

        String token = userService.findUserByUP(user);

        //判断字符串是否为空
        if(StringUtils.hasLength(token)) {
            return SysResult.success(token);
        }else {
            return SysResult.fail();
        }
        //String token = "123456";
        //return SysResult.success(token);
        //return SysResult.fail();
    }


    /*
    * 需求进行分页查询
    * URL: /user/list
    * 请求参数: PageResult
    * 请求返回值: SysResult
    * 请求类型: get请求
    * */
    @GetMapping("/list")
    public SysResult findUserByPage(PageResult pageResult) {
        //携带所有数据返回
        pageResult = userService.findUserByPage(pageResult);
        return SysResult.success(pageResult);
    }

    /*
    * 更新状态信息
    * URL:/user/status/{id}/{status}
    * 参数: id/status
    * 返回值: SysResult
    * */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(User user) {
        userService.updateStatus(user);
        return SysResult.success();
    }

    /*
    * 添加新用户  注意密码加密
    * URL:/user/addUser
    * 参数: 用户的form表单
    * 返回值: SysResult对象
    * */
    @PostMapping("/addUser")
    public SysResult addUser(@RequestBody User user) {
        userService.addUser(user);
        return SysResult.success();
    }

    /*
    * 删除用户
    * URL: /user
    * 返回值 SysResult对象
    * */
    @DeleteMapping("/{id}")
    public SysResult deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return SysResult.success();
    }

    /*
    * 更新用户
    * URL: /user/updateUser
    * 参数: user
    * 返回值 SysResult对象
    * */
    @PutMapping("/updateUser")
    public SysResult updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return SysResult.success();
    }

}
