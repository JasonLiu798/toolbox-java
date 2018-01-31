package com.atjl.util;


import java.util.concurrent.ConcurrentHashMap;

public class PlainTest {


    public static void main(String[] args) {
//        testTimed();
//        System.out.println("test ");
        print();
    }


    public static void print() {
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
        char c = 'A';
        for (int i = 0; i < 16; i++) {
            m.put(String.valueOf(c), String.valueOf(c) + String.valueOf(c));
            c++;
        }

        m.get(String.valueOf(c));


        int tab = 0;
        if (tab == 0) {
            tab = 1;
            System.out.println("set 1");
        } else if (tab == 1) {
            System.out.println("1");
        }
        System.out.println("end");
    }

    public static void print11() {
        int i = 0;
        int n = 16;
        int m = n << 1;
        for (; i < 100; i++) {
            int x = (n - 1) & i;
            int y = (m - 1) & i;
            System.out.println(i + ",x:" + x + ",y:" + y);
        }
        System.out.println("-----------------");

    }


    public static void print1() {
        int i = 0;
        for (; i < 1000 * 1000; i++) {
            int h = i ^ (i >>> 16);
            System.out.println(i + ",h:" + h);
        }
    }


    public static void testTimed() {
        System.out.println("timed called ");
    }
}
