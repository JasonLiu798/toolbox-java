package com.jason.test;

import java.io.Serializable;

/**
 * @author JianLong
 * @date 2016/2/17 15:05
 */
public class TestOverload {

    /*
    public static void say(Object obj) {
        System.out.println("Object");
    }

    public static void say(char obj) {
        System.out.println("char");
    }
    public static void say(int obj) {
        System.out.println("int");
    }

    public static void say(long obj) {
        System.out.println("long");
    }

    public static void say(float obj) {
        System.out.println("float");
    }

    public static void say(double obj) {
        System.out.println("double");
    }

    public static void say(Character obj) {
        System.out.println("Character");
    }

    public static void say(Serializable obj) {
        System.out.println("Serializable");
    }
*/
    public static void say(char... obj) {
        System.out.println("char...");
    }

    public static void main(String[] args) {

        TestOverload.say('a');

    }

}
