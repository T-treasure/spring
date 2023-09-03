package day2;

public class Test01 {

    public static void main(String[] args) {

        MyInterface myInterface1 = new MyInterface() {
            @Override
            public void show(int a, int b) {
                System.out.println(a - b);
            }
        };
        MyInterface myInterface2 = (a, b) -> {
            System.out.println(a + b);
        };
        MyInterface2 myInterface3 = (a, b) -> System.out.println(a + b);
        MyInterface3 myInterface4 = (a, b) -> a - b;
        MyInterface4 myInterface45 = a -> a - 10;
        myInterface1.show(8, 2);
        myInterface2.show(2, 3);
        myInterface3.show(4, 5);
        int a = myInterface4.show(8, 7);
        System.out.println(a);
    }

}
