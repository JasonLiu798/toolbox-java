package com.atjl.jfeild.util;


public class BizObj extends DbObj {

    private String extf1;
    private String extf2;

    private BizObjI1 bizObjI1;
    private BizObjI2 bizObjI2;

    public BizObj() {
    }

    public String getExtf1() {
        return extf1;
    }

    public void setExtf1(String extf1) {
        this.extf1 = extf1;
    }

    public String getExtf2() {
        return extf2;
    }

    public void setExtf2(String extf2) {
        this.extf2 = extf2;
    }

    public BizObjI1 getBizObjI1() {
        return bizObjI1;
    }

    public void setBizObjI1(BizObjI1 bizObjI1) {
        this.bizObjI1 = bizObjI1;
    }

    public BizObjI2 getBizObjI2() {
        return bizObjI2;
    }

    public void setBizObjI2(BizObjI2 bizObjI2) {
        this.bizObjI2 = bizObjI2;
    }
}
