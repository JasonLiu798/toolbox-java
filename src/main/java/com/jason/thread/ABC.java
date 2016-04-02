package com.jason.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author JianLong
 * @date 2013/2/17 16:51
 */
public class ABC {

    private Lock lock = new ReentrantLock();// 通过JDK5中的锁来保证线程的访问的互斥
    private Condition condition = lock.newCondition();// 线程协作
    private int cond = 0;

    class TT implements Runnable {
        private char name;

        public TT(char name) {
            this.name = name;
        }

        public void run() {
            lock.lock();
            try {
                while (true) {
                    int tmp = name-65;
                    int modRes = cond % 3;
//                    System.out.println(name+","+tmp+",mod "+modRes);
                    if ( modRes == tmp ) {
//                        System.out.println(cond + ":" +name);
                        System.out.println(name);
                        cond++;
                        condition.signalAll();
                        break;
                    } else {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void test(){
        Thread t1 = new Thread(new TT('A'));//65
        Thread t2 = new Thread(new TT('B'));//66
        Thread t3 = new Thread(new TT('C'));//67
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0;i<10;i++) {
            executor.execute(t1);
            executor.execute(t2);
            executor.execute(t3);
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        ABC a = new ABC();
        a.test();
    }


}
