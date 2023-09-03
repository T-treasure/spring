package com.zrrd.pojo;

public class User {

    private String conn;    //数据库的连接

    //1.实例化方法
    public User() {
        System.out.println("无参构造！");
    }
    //2.初始化方法
    public void init(){
        this.conn = "赋值数据库连接";
        System.out.println(this.conn);
    }
    //3.用户调用方法
    public void say(){
        System.out.println("用户使用!");
    }
    //4.销毁方法
    public void destroy(){
        this.conn = null;
        System.out.println("释放资源:" + this.conn);
    }
}
