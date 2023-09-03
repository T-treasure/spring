package com.zrrd.factory;

import org.springframework.beans.factory.FactoryBean;

import java.util.Calendar;

public class SpringFactory implements FactoryBean<Calendar> {
    //工厂模式实例方法
    @Override
    public Calendar getObject() throws Exception {
        return Calendar.getInstance();
    }
    //获取类型
    @Override
    public Class<?> getObjectType() {
        return Calendar.class;
    }
}
