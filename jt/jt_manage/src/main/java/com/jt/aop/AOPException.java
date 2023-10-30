package com.jt.aop;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@Component @Aspect @AfterThrowing springBoot会自动帮助我们完成三个注解操作
@RestControllerAdvice //定义全局异常处理  拦截Controller层  返回JSON
public class AOPException {

    //异常通知
    //1.拦截什么类型的异常
    //2.拦截之后怎么处理
    @ExceptionHandler({RuntimeException.class})
    public Object exception(Exception e) {
        //将异常通过控制台输出
        e.printStackTrace();
        return SysResult.fail();
    }


}
