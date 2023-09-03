package com.zrrd.factory;

import java.util.Calendar;


public class InstanceFactory {

    //通过实例化工厂获取对象
    public Calendar getCalendar(){
        return Calendar.getInstance();
    }

}
