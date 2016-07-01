package com.jason798.test;


import org.nustaq.serialization.annotations.Version;

import java.io.Serializable;

/**
 *
 * Created by JasonLiu798 on 16/6/22.
 */
public class TestDto implements Serializable{

    //private static final long serialVersionUID = 3741410167347221748L;

    private int f1;

    private int f2;

    @Version(1)
    private int f3;

    @Version(2)
    private int f4;

    @Version(127)
    private int f5;

    public TestDto(int f1, int f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public TestDto(int f1, int f2, int f3) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

//    @Override
//    public String toString() {
//        return "TestDto{" +
//                "f1=" + f1 +
//                ", f2=" + f2 +
//                ", f3=" + f3 +
//                ", f4=" + f4 +
//                '}';
//    }

    @Override
    public String toString() {
        return "TestDto{" +
                "f1=" + f1 +
                ", f2=" + f2 +
                ", f3=" + f3 +
                ", f4=" + f4 +
                ", f5=" + f5 +
                '}';
    }

//    @Override
//    public String toString() {
//        return "TestDto{" +
//                "f1=" + f1 +
//                ", f2=" + f2 +
//                ", f3=" + f3 +
//                '}';
//    }
}
