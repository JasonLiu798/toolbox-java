package com.jason798.performance;

public class RunTimeStatisticsDto {

    private long max;
    private long min;
    private long avg;
    private long count;
    private long mid;
    private long total;

    public RunTimeStatisticsDto() {
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getAvg() {
        return avg;
    }

    public void setAvg(long avg) {
        this.avg = avg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RunTimeStatisticsDto{" +
                "max=" + max +
                ", min=" + min +
                ", avg=" + avg +
                ", count=" + count +
                ", mid=" + mid +
                ", total=" + total +
                '}';
    }
}
