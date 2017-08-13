package com.atjl.util.thread.task;


public class BaseTask {

    protected boolean start = true;

    protected int id;

    public BaseTask(int id) {
        this.id = id;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void stop(){
        this.start = false;
    }

}
