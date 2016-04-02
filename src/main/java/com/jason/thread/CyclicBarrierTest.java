package com.jason.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author JianLong
 * @date 2016/1/4 14:57
 */
public class CyclicBarrierTest {

        public static void main(String[] args) {
            int count = 1000;
            CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
            ExecutorService executorService = Executors.newFixedThreadPool(count);
            for (int i = 0; i < count; i++) {
//                executorService.execute(new Task(cyclicBarrier));
            }
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

}
