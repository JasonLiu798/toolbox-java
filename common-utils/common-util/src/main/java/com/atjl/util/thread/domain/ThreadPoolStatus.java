package com.atjl.util.thread.domain;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolStatus {
    private String name;
    private String param;

    private boolean notNull;

    private boolean shutdown;
    private int activeCount;
    private long completedTaskCount;
    private int corePoolSize;
    private int largestPoolSize;
    private int poolSize;
    private long taskCount;

    //最大处理时间
    private long maxCostTm;
    //平均处理时间
    private long avgCostTm;

    public long getMaxCostTm() {
        return maxCostTm;
    }

    public void setMaxCostTm(long maxCostTm) {
        this.maxCostTm = maxCostTm;
    }

    public long getAvgCostTm() {
        return avgCostTm;
    }

    public void setAvgCostTm(long avgCostTm) {
        this.avgCostTm = avgCostTm;
    }

    private List<ThreadStatus> runningThreadStatuses;

    public List<ThreadStatus> getRunningThreadStatuses() {
        return runningThreadStatuses;
    }

    public void setRunningThreadStatuses(List<ThreadStatus> runningThreadStatuses) {
        this.runningThreadStatuses = runningThreadStatuses;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    private int queueSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public long getCompletedTaskCount() {
        return completedTaskCount;
    }

    public void setCompletedTaskCount(long completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getLargestPoolSize() {
        return largestPoolSize;
    }

    public void setLargestPoolSize(int largestPoolSize) {
        this.largestPoolSize = largestPoolSize;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public long getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(long taskCount) {
        this.taskCount = taskCount;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public static void main(String[] args) {
        ExecutorService es = null;
        es.isShutdown();
        ThreadPoolExecutor t = null;
        t.getActiveCount();
        t.getCompletedTaskCount();
        t.getCorePoolSize();
        t.getLargestPoolSize();
        t.getPoolSize();
        t.getTaskCount();
        BlockingQueue q = t.getQueue();
        q.size();
    }
    /**
     *         ExecutorService es = null;
     es.isShutdown();
     ThreadPoolExecutor t = null;
     t.getActiveCount();
     t.getCompletedTaskCount();
     t.getCorePoolSize();
     t.getLargestPoolSize();
     t.getPoolSize();
     t.getTaskCount();
     BlockingQueue q = t.getQueue();
     q.size();
     */
//    private
}
