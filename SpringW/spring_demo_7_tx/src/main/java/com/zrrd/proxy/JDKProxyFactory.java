package com.zrrd.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//利用一个工厂动态的为目标对象创建代理
public class JDKProxyFactory {

    public static Object getProxy(Object target) {
       /*
          ClassLoader loader:     类加载器(获取目标对象的Class)
          Class<?>[] interfaces:  JDK代理要求 必须有接口  java中接口可以多实现 所以是数组
          InvocationHandler h:    对目标进行扩展
        */
        //1.获取类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        //2.获取接口数组
        Class<?>[] interfaces = target.getClass().getInterfaces();
        //3.通过动态代理创建对象
        //invoke方法: 代理对象调用的方法时invoke执行，也是扩展方法编辑的位置
        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, (proxy1, method, args) -> {
            //result 表示方法执行的返回值
            Object result = null;
            try {
                //添加事务控制
                System.out.println("事务开启");
                //执行目标方法
                //target真实的目标方法 method方法对象 args方法参数
                result = method.invoke(target, args);
                System.out.println("事务提交");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("事务回滚");
            }
            return result;
        });
        return proxy;
    }

}
