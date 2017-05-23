package com.jason798.test;


import org.nustaq.serialization.annotations.Version;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * Created by JasonLiu798 on 16/6/22.
 */
public class TestDto implements Serializable{

    //private static final long serialVersionUID = 3741410167347221748L;

    public TestDto(){}
    public TestDto(int f1, int f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public TestDto(int f1, int f2, int f3) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

    public TestDto(int f1,String s1){
        this.f1 = f1;
        this.s1 = s1;
    }

    private int f1;

    private int f2;

    @Version(1)
    private int f3;

    @Version(2)
    private int f4;

    @Version(127)
    private int f5;

    private String s1;

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        this.f2 = f2;
    }

    public int getF3() {
        return f3;
    }

    public void setF3(int f3) {
        this.f3 = f3;
    }

    public int getF4() {
        return f4;
    }

    public void setF4(int f4) {
        this.f4 = f4;
    }

    public int getF5() {
        return f5;
    }

    public void setF5(int f5) {
        this.f5 = f5;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
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
                ", s1='" + s1 + '\'' +
                '}';
    }

    public static void main(String[] args) {

        for(int i=0;i<1000;i++) {
            UUID uuid = UUID.randomUUID();

            System.out.println("uuid:" + uuid + ",len " + uuid.toString().length());
        }
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
