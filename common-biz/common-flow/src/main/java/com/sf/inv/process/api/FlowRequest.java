package com.sf.inv.process.api;

/**
 * flow request
 */
public class FlowRequest <T>{
    private T param;

    public FlowRequest(T param) {
        this.param = param;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "FlowRequest{" +
                "param=" + param +
                '}';
    }
}
