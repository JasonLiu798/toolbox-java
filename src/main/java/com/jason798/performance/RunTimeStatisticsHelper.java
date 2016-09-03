package com.jason798.performance;

import com.jason798.collection.CollectionHelper;
import com.jason798.collection.SearchHelper;
import com.jason798.number.NumberHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;



public class RunTimeStatisticsHelper {

    private static ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
    //private static ThreadLocal<List<RunTimeDto>> runTimeList = new ThreadLocal<>();


    public static void addRunTimeDto(RunTimeDto rtd){
        queue.add(rtd);
        /*
        List<RunTimeDto> list = queue.get();
        if(list ==null ){
            list = new LinkedList<>();
            runTimeList.set(list);
        }*/
    }


    public static String getResultString(){
        /*
        List<RunTimeDto> list = runTimeList.get();
        StringBuilder sb = new StringBuilder();
        for(RunTimeDto dto:list){
            String line = RunTimeHelper.getFmtTimeForStdout(dto);
            sb.append(line);
        }
        return sb.toString();
        */
        return null;
    }


    public static String getStatisticsFormat(){
        return getStatistics().toString();
    }



    /**
     * generate statitics
     *
     * total cost time
     * average cost time
     * max cost time
     *
     * @return
     */
    public static RunTimeStatisticsDto getStatistics(){
        RunTimeDto[] arr = (RunTimeDto[]) queue.toArray(new RunTimeDto[queue.size()]);
        //List<RunTimeDto> list = runTimeList.get();
        if(CollectionHelper.isEmpty(arr)){
            return null;
        }
        long totalTime = 0;
        long count = 0;
        long max = 0;
        long min = 0;
        boolean first = true;
        ArrayList<Long> times = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            RunTimeDto rtd =  arr[i];
            //List<Long> times = rtd.getTimes();
            List<Long> costTimes = rtd.getIntervalTimes();

            for(Long cost:costTimes){
                if(first) {
                    max = cost;
                    min = cost;
                    first = false;
                }else{
                    if(cost>max){
                        max = cost;
                    }
                    if(cost<min){
                        min = cost;
                    }
                }
                totalTime+=cost;
                count+=1;

            }
            times.addAll(costTimes);
        }
        int mid = times.size()%2==0?times.size()/2:times.size()/2+1;
        Long positiong = SearchHelper.randomizedSelect(times.toArray(new Long[times.size()]), new Comparator<Long>() {
            public int compare(Long o1, Long o2) {
                return o1.intValue() - o2.intValue();
            }
        }, mid);

//        Integer positiong = SearchHelper.randomizedSelect(ints, new Comparator<Integer>() {
//            public int compare(Integer o1, Integer o2) {
//                return o1.intValue() - o2.intValue();
//            }
//        }, ints.length/2);
        RunTimeStatisticsDto res = new RunTimeStatisticsDto();
        res.setAvg( totalTime/count);
        res.setMax(max);
        res.setMin(min);
        res.setCount(count);
        res.setTotal(totalTime);
        res.setMid(positiong);
        return res;
    }

    public static void clear(){
        //runTimeList.set(null);
    }

}
