package com.atjl.dbtiming.core;


import com.atjl.dbtiming.domain.biz.QueueWaitTask;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 等待执行队列
 * input:调度线程
 * output:执行线程
 */
public class WaitExecuteQueue {

    private ArrayBlockingQueue<QueueWaitTask> queue = new ArrayBlockingQueue<>(50000);

    private WaitExecuteQueue() {
    }

    private static class Holder {
        private static WaitExecuteQueue holder = new WaitExecuteQueue();
    }

    public static WaitExecuteQueue getInstence() {
        return Holder.holder;
    }

    /**
     * no wait
     */
    public QueueWaitTask poll() {
        return queue.poll();
    }

    /**
     * will wait
     */
    public QueueWaitTask take() throws InterruptedException {
        return queue.take();
    }


    public QueueWaitTask peek() {
        return queue.peek();
    }

    public void offer(QueueWaitTask msg) {
        queue.offer(msg);
    }

    public void clear() {
        queue.clear();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
