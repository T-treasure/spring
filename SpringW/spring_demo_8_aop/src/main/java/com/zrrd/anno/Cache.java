package com.zrrd.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//控制注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
//控制注解的作用对象  方法有效  类有效
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface Cache {

}
