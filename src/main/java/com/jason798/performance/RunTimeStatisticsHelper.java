package com.jason798.performance;

import com.jason798.collection.CollectionHelper;
import com.jason798.collection.SearchHelper;
import com.jason798.number.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class RunTimeStatisticsHelper {

    private static ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
    //private static ThreadLocal<List<RunTimeDto>> runTimeList = new ThreadLocal<>();
    public static void addRunTimeDto(RunTimeDto rtd) {
        queue.add(rtd);
    }

    public static String getStatisticsFormat() {
        return getStatistics().toString();
    }

    /**
     * generate statitics
     * <p>
     * total cost time
     * average cost time
     * max cost time
     *
     * @return
     */
    public static RunTimeStatisticsDto getStatistics() {
        RunTimeDto[] arr = (RunTimeDto[]) queue.toArray(new RunTimeDto[queue.size()]);
        //List<RunTimeDto> list = runTimeList.get();
        if (CollectionHelper.isEmpty(arr)) {
            return null;
        }
        ArrayList<Long> times = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            RunTimeDto rtd = arr[i];
            List<Long> costTimes = rtd.getIntervalTimes();
            times.addAll(costTimes);
        }
        return getStat(
                times.toArray(new Long[times.size()]),
                (o1, o2) -> NumberHelper.long2Int(o1 - o2),
                new LongCalculator(),
                num -> num);
    }


    /**
     * get statistics
     *
     * @param list list of long
     * @return stat info
     */
    public static <T> RunTimeStatisticsDto getStat(T[] list, Comparator<T> cmp, Calculator<T> calc, LongCoverter<T> cov) {
        //ArrayList<Long> times = new ArrayList<>();
        if (CollectionHelper.isEmpty(list)) {
            return new RunTimeStatisticsDto();
        }
        boolean first = true;
        T max = null;
        T min = null;
        T sum = null;
        long count = 0;
        Map<T, Long> countMap = new HashMap<>();
        //TODO: optimize to compare two element one time
        if (list.length == 1) {
            RunTimeStatisticsDto res = new RunTimeStatisticsDto(list[0]);
            return res;
        }
        count = list.length;
        for (int i = 0, j = 1; i < list.length || j < list.length + 1; i += 2, j += 2) {
            T it = list[i];
            if (first) {
                first = false;
                T ij = list[j];
                int cmpRet = cmp.compare(it, ij);
                if (cmpRet < 0) {
                    min = it;
                    max = ij;
                } else {
                    min = ij;
                    max = it;
                }
                addMapCounter(countMap, it);
                addMapCounter(countMap, ij);
                sum = calc.add(sum, calc.add(it, ij));
            } else {
                if (j > list.length - 1) {//i is the last one
                    int cmpMaxRet = cmp.compare(it, max);
                    if (cmpMaxRet > 0) {
                        max = it;
                    }
                    int cmpMinRet = cmp.compare(it, min);
                    if (cmpMinRet < 0) {
                        min = it;
                    }
                    addMapCounter(countMap, it);
                    sum = calc.add(sum, it);
                } else {//process pair
                    T ij = list[j];
                    int cmpRet = cmp.compare(it, ij);
                    if (cmpRet < 0) {
                        min = getMin(min, it, cmp);
                        max = getMax(max, it, cmp);
                    } else {
                        min = getMin(ij, min, cmp);
                        max = getMax(it, max, cmp);
                    }
                    addMapCounter(countMap, it);
                    addMapCounter(countMap, ij);
                    sum = calc.add(sum, calc.add(it, ij));
                }
            }
        }

        T positiong = getMid(list, cmp);
        T median = getMedian(countMap, positiong);
        RunTimeStatisticsDto res = new RunTimeStatisticsDto();
        res.setAvg(calc.divide(sum, cov.covert(count)));
        res.setMax(max);
        res.setMin(min);
        res.setCount(count);
        res.setTotal(sum);
        res.setMid(positiong);
        res.setMedian(median);
        return res;
    }

    private static <T> T getMin(T n1, T n2, Comparator<T> cmp) {
        int cmpMinRet = cmp.compare(n1, n2);
        T res = n1;
        if (cmpMinRet > 0) {
            res = n2;
        }
        return res;
    }

    private static <T> T getMax(T n1, T n2, Comparator<T> cmp) {
        int cmpMinRet = cmp.compare(n1, n2);
        T res = n1;
        if (cmpMinRet < 0) {
            res = n2;
        }
        return res;
    }

    public static <T> T getMedian(Map<T, Long> countMap, T mid) {
        T median = null;
        long maxCnt = 0;
        boolean first = true;
        boolean allOne = true;
        for (T num : countMap.keySet()) {
            if (first) {
                maxCnt = countMap.get(num);
                median = num;
                first = false;
            } else {
                if (countMap.get(num) > maxCnt) {
                    allOne = false;
                    maxCnt = countMap.get(num);
                    median = num;
                }
            }
        }
        if (allOne) {
            median = mid;
        }
        return median;
    }

    public static <T> T getMid(T[] arr, Comparator<T> cmp) {
        int i2find = arr.length % 2 == 0 ? arr.length / 2 : arr.length / 2 + 1;
        T midVal = SearchHelper.randomizedSelect(arr, cmp, i2find);
        return midVal;
    }

    private static <T> void addMapCounter(Map<T, Long> map, T key) {
        Long cnt = map.get(key);
        if (cnt == null) {
            map.put(key, 1L);
        } else {
            map.put(key, cnt + 1);
        }
    }

    public static void clear() {
        //runTimeList.set(null);
    }

}
