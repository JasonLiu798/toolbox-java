package com.jason798.performance;

import java.util.HashMap;

/**
 * For test,record cost time and return value
 * @author JianLong
 * @date 2015/4/6 12:30
 */
public class ResCostTime <T>{

    private T data;

    private HashMap<String,Long> time;

    public ResCostTime(){

    }

    public ResCostTime( T data,HashMap<String,Long> time) {
        this.time = time;
        this.data = data;
    }


    public void setTime(HashMap<String, Long> time) {
        this.time = time;
    }

    public HashMap<String, Long> getTime() {
        return time;
    }

    public void setData(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResCostTime{" +
                "data=" + data +  ", time=" + time +"}";
    }
}
