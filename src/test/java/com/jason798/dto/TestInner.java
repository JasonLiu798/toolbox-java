package com.jason798.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class TestInner implements Serializable {
    @Override
    public String toString() {
        return "TestInner{" +
                "name='" + name + '\'' +
                ", isProvide=" + isProvide +
                '}';
    }

    private String name;

    @JSONField(name="isProvide")
    private Boolean isProvide;

    public TestInner(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getProvide() {
        return isProvide;
    }

    public void setProvide(Boolean provide) {
        isProvide = provide;
    }

    //    public Boolean getIsProvide() {
//        return isProvide;
//    }
//
//    public void setIsProvide(Boolean provide) {
//        isProvide = provide;
//    }
}
