package com.javase;

public class Test02 {

    public static void main(String[] args) {


        String str1 = "1230";
        String str2 = "1230";
        //常量池
        String str3 = new String("123");
        System.out.println(str1.equals(str2));
        System.out.println(str1.equals(str3));

        //整数池
        //一个byte大小
        //-128 ~  127
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 300;
        Integer i4 = 300;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }

}
