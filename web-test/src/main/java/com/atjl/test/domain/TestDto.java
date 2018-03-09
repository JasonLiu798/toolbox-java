package com.atjl.test.domain;


public class TestDto {

    public TestDto() {
    }

    private String a;
    private String b;

    @Override
    public String toString() {
        return "TestDto{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
