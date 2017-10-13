package com.atjl.dbtiming.domain.biz;

import com.atjl.dbtiming.domain.BaseTimingTask;

import java.io.Serializable;

/**
 * 新建完毕后，放入等待队列的 task
 */
public class QueueWaitTask implements Serializable {

    private static final long serialVersionUID = 5202528035414355566L;
    /**
     * 下次执行绝对时间
     */
    private long nextExecuteTs;

    /**
     * 主键
     */
    private long tid;

    private BaseTimingTask task;

    /**
     * 时间差太大直接丢弃
     */
    private boolean diffTooBigDrop = false;

    public QueueWaitTask(long nextExecuteTs, long tid, BaseTimingTask task) {
        this.nextExecuteTs = nextExecuteTs;
        this.tid = tid;
        this.task = task;
    }

    public BaseTimingTask getTask() {
        return task;
    }

    public void setTask(BaseTimingTask task) {
        this.task = task;
    }

    public long getNextExecuteTs() {
        return nextExecuteTs;
    }

    public void setNextExecuteTs(long nextExecuteTs) {
        this.nextExecuteTs = nextExecuteTs;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public boolean isDiffTooBigDrop() {
        return diffTooBigDrop;
    }

    public void setDiffTooBigDrop(boolean diffTooBigDrop) {
        this.diffTooBigDrop = diffTooBigDrop;
    }
//是否已经持久化,动态新增-false, 再次执行的为-true
    //private boolean persist;


//    private
}
