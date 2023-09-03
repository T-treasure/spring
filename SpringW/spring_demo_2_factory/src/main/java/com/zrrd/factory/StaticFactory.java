package com.zrrd.factory;

import java.util.Calendar;

public class StaticFactory {

    //通过静态工厂获取对象
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

}
