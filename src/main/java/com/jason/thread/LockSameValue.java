package com.jason.thread;

import com.jason.common.DateUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author JianLong
 * @date 2013/10/27 15:02
 */
public class LockSameValue {

    private final static Map<String, ReentrantLock> relations = new ConcurrentHashMap<String, ReentrantLock>();

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            relations.put(String.valueOf(i), new ReentrantLock());
        }
        for (int i = 0; i < 9; i++) {
            Worker wk = new Worker(i);
            Thread t = new Thread(wk);
            t.start();
        }
    }

    static class Worker implements Runnable {
        private int id;

        public Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
//            System.out.println(id + " start & into func " + System.currentTimeMillis());
            String str = String.valueOf(id % 3);
            ReentrantLock lock = relations.get(str);
            lock.lock();
            try{
                System.out.println(id + " sync "+ DateUtil.getNowLastNTS(6));
                Thread.sleep(3000);
                System.out.println(id + " out sync " + DateUtil.getNowLastNTS(6) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }
}
