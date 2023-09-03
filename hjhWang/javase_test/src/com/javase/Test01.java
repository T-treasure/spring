package com.javase;

/**
 * @author : Li
 */
public class Test01 {

    public static void main(String[] args) {
        Person p = new Son("张三", 15);
        p.eat();
    }
}
class Person {
    String name;
    int age;
    //this表示本类当前对象
    public Person(String name, int age) {
        //变量是有就近原则
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("吃饭");
    }
}
class Son extends Person{

    public Son(String name, int age) {
        //表示父类对象,不管super或者this必须在逻辑第一行
        super(name, age);
    }

    @Override
    public void eat() {
//        super.eat();
        System.out.println("吃肉");
    }
}
