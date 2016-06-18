package com.jason798.performance;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 计时工具
 * Created by JasonLiu798 on 16/4/5.
 */
public class RunTimeDto implements Serializable {
    private static final long serialVersionUID = -2845545161310101281L;
    private List<Long> times = new LinkedList<>();
    private List<String> comments = new LinkedList<>();

    public RunTimeDto(){
    }

    public void addTime(){
        times.add(System.currentTimeMillis());
        if(times.size()>1) {
            comments.add("unknown");
        }
    }

    public void addTime(String comment){
        times.add(System.currentTimeMillis());
        if(times.size()>1) {//first comment will be drop
            comments.add(comment);
            //comments.add("unknown");
        }
    }

    public List<Long> getTimes() {
        return times;
    }

    public List<String> getComments() {
        return comments;
    }

    public boolean hasTime(){
        if(times!=null && times.size()>1){
            return true;
        }
        return  false;
    }

    public boolean hasComment(){
        if(comments!=null && comments.size()>=1){
            return true;
        }
        return  false;
    }


}
