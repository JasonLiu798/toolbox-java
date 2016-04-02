package com.jason.thread;

import com.jason.common.BeanHelper;

import java.util.concurrent.CyclicBarrier;

/**
 * @author JianLong
 * @date 2014/1/4 14:57
 */
public class Task implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private Object src;

    public Task(CyclicBarrier cyclicBarrier,Object src) {
        this.src = src;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // 等待所有任务准备就绪
            cyclicBarrier.await();
            // 测试内容
            Object obj = new Object();
            BeanHelper.copyProperties(obj, src);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
