package com.jason798.aconcurrent;

import com.jason798.collection.CollectionUtil;
import org.junit.Test;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestConcurrent {

    @Test
    public void test() throws InterruptedException {

        CountDownLatch l = new CountDownLatch(2);
        T1 t1 = new T1("t1",  l);
        T1 t2 = new T1("t2",  l);
        ExecutorService es = Executors.newFixedThreadPool(10);
        es.submit(t1);
        es.submit(t2);

        Thread.sleep(100000L);
    }

    public static class T1 implements Runnable {
        private CountDownLatch latch;
        private String key;

        public T1(String key, CountDownLatch latch) {
            this.key = key;
            this.latch = latch;
        }

        public void run() {
            long id = Thread.currentThread().getId();
            try {
                latch.countDown();
                latch.await();
                System.out.println(key+"thread start run " + id);
                Map res = CollectionUtil.addKV(CollectionUtil.newMap(id,id),id+1,id+1);
                System.out.println(key+"res:" + res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
