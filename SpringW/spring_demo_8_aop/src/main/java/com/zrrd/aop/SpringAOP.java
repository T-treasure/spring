package com.zrrd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//1.Aop需要被Spring容器进行管理
@Component
//2.标识该类为AOP切面
//Spring默认不能识别切面注解
@Aspect
public class SpringAOP {

    /*
     * 切点表达式
     * 1.within(com.zrrd.*.DeptServiceImpl) 一级包下的类
     * 2.within(com.zrrd..*.DeptServiceImpl) ..代表多级包下的类
     * 3.within(com.zrrd..*) 包下使用的类
     *
     * execution(返回值类型  包名.类名.方法名(参数列表))
     * //返回值类型任意的  com.zeed下的所有包中的DeptServiceImpl类开头的方法 并且没有参数
     * 1.execution(* com.zrrd..*.DeptServiceImpl.add*())
     * //返回值类型任意 com.zrrd包下的所有包中的所有类中所有的方法
     * 2.execution(* com.zrrd..*.*(..))
     * //强调Spring表达式中是没有自动拆箱装箱功能 所有注意参数
     * 3.execution(int com.zrrd..*.*(int))
     * 3.execution(Integer com.zrrd..*.*(Integer))
     *
     * //只拦截特定的注解内容
     * @annotation(包名.注解名)
     * @annotation(com.zrrd.anno.Cache)
     */

    //1.定义before通知
    //@Before("bean(deptServiceImpl)")
    //@Before("execution(* com.zrrd..*.DeptServiceImpl.add*())")
    //@Before("within(com.zrrd..*)")
    //1.定义切入点表达式
    @Pointcut("@annotation(com.zrrd.anno.Cache)")
    public void pt() {}

    /*
    * Spring为了获取AOP动态获取目标对象及方法中的数据 则通过JoinPoint对象
    * 进行数据的传递
    * getSignature:  获取方法签名  获取方法的参数  int com.zrrd.Service.DeptService.addSum(int)
    *                                          void com.zrrd.Service.DeptService.addDept()
    * getDeclaringTypeName 获取类名
    * */
//    @Before("pt()")
    public void before(JoinPoint point) {
        System.out.println("获取目标对象的类型:" + point.getClass());
        System.out.println("获取目标对象的类名:" + point.getSignature().getDeclaringTypeName());
        System.out.println("获取目标对象方法:" + point.getSignature().getName());
        System.out.println("获取方法参数：" + Arrays.toString(point.getArgs()));
        System.out.println("我是before前置通知！");
    }

    /*
     * point: 关联的切入点表达式
     * returning: 将方法的返回值 通过形参进行传递
     * @AfterReturning(pointcut = "pt()", returning = "result")
     * 注意事项: JoinPoint joinPoint必须在第一个位置
     */
//    @AfterReturning(pointcut = "pt()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("返回值: " + result);
        System.out.println("我是afterReturning通知");
    }

//    @AfterThrowing(pointcut = "pt()", throwing = "e")
    public void afterThrowing(Exception e) {
        System.out.println("获取异常信息:" + e.getMessage());
        System.out.println("获取异常类型：" + e.getClass());
        System.out.println("我是afterThrowing通知");
    }

//    @After("pt()")
    public void after() {
        System.out.println("我是after返回通知！");
    }

//    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;

        System.out.println("环绕通知开始");
        Long start = System.currentTimeMillis();
        try {
            //相当于执行目标方法
            result = joinPoint.proceed();
            Long end = System.currentTimeMillis();
            System.out.println("耗时: " + (end - start) + "毫秒");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("环绕通知结束");
        return result;
    }



}
