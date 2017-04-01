package com.jason798.dto;


import java.io.Serializable;
import java.util.List;

public class TestDto1 implements Serializable {
    private Long loopId;
    private List<TestInner> testInner;

    @Override
    public String toString() {
        return "TestDto1{" +
                "loopId=" + loopId +
                ", testInner=" + testInner +
                '}';
    }

    public TestDto1(){}

    public Long getLoopId() {
        return loopId;
    }

    public void setLoopId(Long loopId) {
        this.loopId = loopId;
    }

    public List<TestInner> getTestInner() {
        return testInner;
    }

    public void setTestInner(List<TestInner> testInner) {
        this.testInner = testInner;
    }
}
