package com.atjl.util.thread.domain;


public class ThreadStatus {
    private String id;

    private String state;

    private Long startTs;


    public Long getStartTs() {
        return startTs;
    }

    public void setStartTs(Long startTs) {
        this.startTs = startTs;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
