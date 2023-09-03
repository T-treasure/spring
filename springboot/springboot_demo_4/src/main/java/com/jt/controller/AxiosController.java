package com.jt.controller;

import com.jt.pojo.AxiosPojo;
import org.springframework.web.bind.annotation.*;

@RestController //在后端接收前端接收的axios发来的请求
@CrossOrigin //允许当前类中的所有方法实行跨域操作
public class AxiosController {

    /*
    * Axios入门案例
    * url: http://localhost/8090/hello
    * */
    @GetMapping("/hello")
    public String hello() {
        return "VUE的Ajax异步调用";
    }

    /*
    * 1.接收get请求的id参数
    * url: http://localhost/8090/axios?id=100
    * */
    @GetMapping("/axios")
    public String getAxios(Integer id) {
        return "动态获取数据 " + id;
    }

    /*
    * restFul风格接收参数
    * url: http://localhost/8090/axios/100
    * */
    @GetMapping("/axios/{id}")
    public String axiosRestFul(@PathVariable Integer id) {
        return "restFul获取数据 " + id;
    }

    /*
    * 测试params对象数据传参
    * */
    @GetMapping("/axiosParams")
    public String params(Integer id) {
        return "获取params参数成功";
    }

    /*
    * 动态接收post请求  并且接收Axios参数
    * url地址: /addAxios
    * 如果前端传递的是一个json字符串
    * 则需要@RequestBody 将json串转化为对象
    * */
    @PostMapping("/addAxios")
    public AxiosPojo addAxios(@RequestBody AxiosPojo axiosPojo) {
        return axiosPojo;
    }

    /*
    * 利用Form表单接收数据
    * id:100 name:tom 可以使用对象节后参数
    * */
    @PostMapping("/addAxiosForm")
    public AxiosPojo addAxiosForm(AxiosPojo axiosPojo) {
        return axiosPojo;
    }
}
