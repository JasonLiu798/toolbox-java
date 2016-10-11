package com.jason798.performance;

import com.jason798.collection.CollectionUtil;

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

    /**
     * add current time ms
     */
    public void addTime(){
        addTime("unknown");
    }

    /**
     * add current time and comment
     * @param comment comment
     */
    public void addTime(String comment){
        times.add(System.currentTimeMillis());
        if(times.size()>1) {//first comment will be drop
            comments.add(comment);
        }
    }

    /**
     * get time list
     * @return time list
     */
    public List<Long> getTimes() {
        return times;
    }

    /**
     * get interval times
     * @return interval time list
     */
    public List<Long> getIntervalTimes(){
        if(CollectionUtil.isEmpty(times)){
            return null;
        }
        List<Long>  res = new LinkedList<>();
        for(int j=1,i=0;j<times.size();i++,j++){
            Long t0 = times.get(i);
            Long t1 = times.get(j);
            res.add( t1-t0);
        }
        return res;
    }

    /**
     * get total cost time
     * @return cost time
     */
    public Long getTotalCostTime(){
        List<Long> intlist = getIntervalTimes();
        if(CollectionUtil.isEmpty(intlist)){
            return 0L;
        }
        long res = 0;
        for(Long t : intlist){
            res+=t;
        }
        return res;
    }

    /**
     * get time count
     * @return time count
     */
    public int getCount(){
        if(CollectionUtil.isEmpty(times)){
            return 0;
        }
        return times.size();
    }

    /**
     * get comment
     * @return comments
     */
    public List<String> getComments() {
        return comments;
    }

    public boolean hasTime(){
        if(times!=null && times.size()>1){
            return true;
        }
        return  false;
    }


    /**
     * has got comment
     * @return has comment or not
     */
    public boolean hasComment(){
        if(comments!=null && comments.size()>=1){
            return true;
        }
        return  false;
    }

}
