package com.jason798.performance;

public class RunTimeStatisticsDto <T>{
    private T total;
    private long count;
    private T max;
    private T min;
    private T avg;
    private T mid;
    private T median;


    public RunTimeStatisticsDto(){}

    public RunTimeStatisticsDto(T one) {
        count = 1;
        total = max=min=mid=avg=median=one;
    }


    @Override
    public String toString() {
        return "RunTimeStatisticsDto{" +
                "total=" + total +
                ", count=" + count +
                ", max=" + max +
                ", min=" + min +
                ", avg=" + avg +
                ", mid=" + mid +
                ", median=" + median +
                '}';
    }

    public T getTotal() {
        return total;
    }

    public void setTotal(T total) {
        this.total = total;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getAvg() {
        return avg;
    }

    public void setAvg(T avg) {
        this.avg = avg;
    }

    public T getMid() {
        return mid;
    }

    public void setMid(T mid) {
        this.mid = mid;
    }

    public T getMedian() {
        return median;
    }

    public void setMedian(T median) {
        this.median = median;
    }
}