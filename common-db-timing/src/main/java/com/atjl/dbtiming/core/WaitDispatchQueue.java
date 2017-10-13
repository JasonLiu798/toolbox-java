package com.atjl.dbtiming.core;


import com.atjl.dbtiming.domain.comparator.QueueWaitTaskComparator;
import com.atjl.dbtiming.domain.biz.QueueWaitTask;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 等待调度队列
 * input:接收任务service(多线程)
 * output:调度线程(单线程)
 */
public class WaitDispatchQueue {

    private PriorityBlockingQueue<QueueWaitTask> queue = new PriorityBlockingQueue<>(50000, new QueueWaitTaskComparator());

    private WaitDispatchQueue() {
    }

    private static class Holder {
        private static WaitDispatchQueue holder = new WaitDispatchQueue();
    }

    public static WaitDispatchQueue getInstence() {
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

    public void put(QueueWaitTask msg) {
        queue.put(msg);
    }

    public void clear() {
        queue.clear();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
