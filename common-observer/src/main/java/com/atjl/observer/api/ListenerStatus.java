package com.atjl.observer.api;


public class ListenerStatus {
    private String className;
    private String event;
    private Long fireCount;
    private Long lastCost;

    public String getClassName() {
        return className;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getFireCount() {
        return fireCount;
    }

    public void setFireCount(Long fireCount) {
        this.fireCount = fireCount;
    }

    public Long getLastCost() {
        return lastCost;
    }

    public void setLastCost(Long lastCost) {
        this.lastCost = lastCost;
    }
}
