package com.jason798.dto;


import java.io.Serializable;

public class TestDto implements Serializable {
    private Long loopId;

    private TestInner testInner;

    public TestDto(){}

    public Long getLoopId() {
        return loopId;
    }

    public void setLoopId(Long loopId) {
        this.loopId = loopId;
    }

    public TestInner getTestInner() {
        return testInner;
    }

    public void setTestInner(TestInner testInner) {
        this.testInner = testInner;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "loopId=" + loopId +
                ", testInner=" + testInner +
                '}';
    }
}
