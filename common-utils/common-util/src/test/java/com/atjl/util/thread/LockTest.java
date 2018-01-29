package com.atjl.util.thread;


import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.thread.task.BaseTask;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    @Test
    public void testCountDown() {
        String pool = "P1";
        ThreadPoolManager.init(CollectionUtil.newList(pool + ThreadConstant.IO_POOL_PARAM));
        CountDownLatch c = new CountDownLatch(10);
        ThreadPoolManager.execute(pool, new BaseTask() {
            @Override
            protected void bizRun() {
                c.countDown();
            }
        });

        ThreadPoolManager.execute(pool, new BaseTask() {
            @Override
            protected void bizRun() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * synchronized dead lock
     */
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    @Test
    public void testSyncDeadLock() {

        String pool = "P1";
        ThreadPoolManager.init(CollectionUtil.newList(pool + ThreadConstant.IO_POOL_PARAM));
        CountDownLatch c = new CountDownLatch(2);

        ThreadPoolManager.execute(pool, new BaseTask() {
            @Override
            protected void bizRun() {
                c.countDown();
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    SystemUtil.sleep(1000);
                    synchronized (B) {
                        synchronized (C) {
                            System.out.println("get A,B");
                        }
                    }
                }
            }
        });

        ThreadPoolManager.execute(pool, new BaseTask() {
            @Override
            protected void bizRun() {
                c.countDown();
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (C) {
                    SystemUtil.sleep(1000);
                    synchronized (B) {
                        synchronized (A) {
                            System.out.println("get B,A");
                        }
                    }
                }
            }
        });
        SystemUtil.sleepForever();
        System.out.println("end");
    }

    /**
     * ReentrantLock dead lock
     */
    @Test
    public void testDeadLock() {

        ReentrantLock l1 = new ReentrantLock();
        ReentrantLock l2 = new ReentrantLock();

        String pool = "P1";
        ThreadPoolManager.init(CollectionUtil.newList(pool + ThreadConstant.IO_POOL_PARAM));
        CountDownLatch c = new CountDownLatch(2);

        ThreadPoolManager.execute(pool, new BaseTask() {
            @Override
            protected void bizRun() {
                c.countDown();
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                l1.lock();
                SystemUtil.sleep(1000);
                l2.lock();
                System.out.println("get A,B");
                l2.unlock();
                l1.unlock();
            }
        });

        ThreadPoolManager.execute(pool, new BaseTask() {
            @Override
            protected void bizRun() {
                c.countDown();
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                l2.lock();
                SystemUtil.sleep(1000);
                l1.lock();
                System.out.println("get B,A");
                l1.unlock();
                l2.unlock();
            }
        });
        SystemUtil.sleepForever();
        System.out.println("end");
    }
}
