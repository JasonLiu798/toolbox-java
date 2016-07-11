package com.jason798.performance;


import java.util.HashMap;
import java.util.List;

/**
 * For simpledemo, calc avg,max,min cost time.
 * @author JasonLiu798
 * @date 2015/4/6 12:30
 */
public class ResCostTimeCalc<T>{
    /**
     * key->List->ResCostTime0->key1->time1
     *                        ->key2->time2
     *          ->ResCostTime1->key->time
     *          ->ResCostTime2->key->time
     *
     */
    private List<ResCostTime<T>> resCostTimes;
    private HashMap<String,Long> maxTimeHm = new HashMap<>();
    private HashMap<String,Long> minTimeHm = new HashMap<>();
    private HashMap<String,Long> avgTimeHm = new HashMap<>();

    public ResCostTimeCalc(List<ResCostTime<T>> resCostTimes) {
        this.resCostTimes = resCostTimes;
    }

    /**
     * calc max time
     * @param key
     * @return
     */
    public long getMaxTime(String key) {
        HashMap<String,Long> tmp;
        long maxTime = 0;
        int i=0;
        //for(Map.Entry<String,ResCostTime<T>> cot:resCostTimes){
        //List<ResCostTime<T>> rcts = resCostTimes.get(key);
        for( ResCostTime<T> rct:resCostTimes){
            tmp = rct.getTime();
            if(i==0){
                maxTime = tmp.get(key);
            }else{
                long time = tmp.get(key);
                if( time >maxTime){
                    maxTime = time;
                }
            }
            i++;
        }
        maxTimeHm.put(key,maxTime);
        return maxTime;
    }

    /**
     * calc min time
     * @param key
     * @return
     */
    public long getMinTime(String key) {
        HashMap<String,Long> tmp;
        long minTime = 0;
        int i=0;
        //List<ResCostTime<T>> rcts = resCostTimes.get(key);
        for(ResCostTime<T> cot:resCostTimes){
            if(i==0){
                tmp = cot.getTime();
                minTime = tmp.get(key);
            }else{
                long time = cot.getTime().get(key);
                if( time <minTime){
                    minTime = time;
                }
            }
            i++;
        }
        minTimeHm.put(key,minTime);
        return minTime;
    }

    /**
     * calc avg time
     * @param key
     * @return
     */
    public long getAvgTime(String key) {
        HashMap<String,Long> tmp;
        long sum = 0;
        int count = resCostTimes.size();
        //List<ResCostTime<T>> rcts = resCostTimes.get(key);
        for(ResCostTime<T> cot:resCostTimes){
            long time = cot.getTime().get(key);
            sum +=time;
        }
        long avg = sum/count;
        avgTimeHm.put(key,avg);
        return avg;
    }

    /**
     * format cost time of one record to string
     * @param key
     * @return
     */
    public String formatTime(String key){
        StringBuffer sb = new StringBuffer();
        int i=0;
        //List<ResCostTime<T>> rcts = resCostTimes.get(key);
        for(ResCostTime<T> cot:resCostTimes){
            long time = cot.getTime().get(key);
            sb.append(i).append(" cost ").append(time).append(" ms\n");
            i++;
        }
        return sb.toString();
    }

    /**
     * format aggrate time to string
     * @param key
     * @return
     */
    public String getFormatAgTime(String key){
        StringBuffer sb = new StringBuffer();
        this.getMinTime(key);
        sb.append(key).append(" min cost ").append(minTimeHm.get(key)).append(" ms\n");
        this.getMaxTime(key);
        sb.append(key).append(" max cost ").append(maxTimeHm.get(key)).append(" ms\n");
        this.getAvgTime(key);
        sb.append(key).append(" avg cost ").append(avgTimeHm.get(key)).append(" ms\n");
        return sb.toString();
    }

    /**
     * format all cost time to str
     * @param key
     * @return
     */
    public String getFormatAllTime(String key){
        StringBuffer sb = new StringBuffer();
        sb.append(key+" time cost result:\n");
        sb.append(formatTime(key));
        sb.append(getFormatAgTime(key));
        return sb.toString();
    }

}
