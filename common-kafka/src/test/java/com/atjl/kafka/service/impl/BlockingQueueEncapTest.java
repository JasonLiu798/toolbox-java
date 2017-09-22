package com.atjl.kafka.service.impl;

import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.impl.BlockingQueueEncap;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * BlockingQueueEncap
 * @since 1.0
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class BlockingQueueEncapTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    public AtomicInteger i = new AtomicInteger(0);
    HashMap<Integer,Integer> hashMap = new HashMap<>();

    @Test
    public void testSingleProducer() throws Exception {
        IQueue<Integer> q = new BlockingQueueEncap<>(10);

        Thread tp = new Thread(new Producer(q,1,10));
        tp.start();
        for(int i=0;i<3;i++) {
            hashMap.put(i,0);
//            Consumer c = new Consumer(q, 1);
            Thread cp  = new Thread(new Consumer(q,i,30));
            cp.start();
        }
        Thread sp = new Thread(new Statics());
        sp.start();
        tp.join();
    }

    @Test
    public void testSingleProduceLinkedQueue() throws Exception {
        IQueue<Integer> q = new BlockingQueueEncap(10);

        Thread tp = new Thread(new Producer(q,1,10));
        tp.start();
        for(int i=0;i<5;i++) {
            hashMap.put(i,0);
            Thread cp  = new Thread(new Consumer(q,i,300));
            cp.start();
        }
        Thread sp = new Thread(new Statics());
        sp.start();
        tp.join();
    }


    @Test
    public void testMultiProducer() throws Exception {
        IQueue<Integer> q = new BlockingQueueEncap<>(10);
        for(int i=0;i<3;i++) {
            Thread tp = new Thread(new Producer(q, i,90));
            tp.start();
        }
        hashMap.put(1,0);
        Thread cp  = new Thread(new Consumer(q,1,10));
        cp.start();
        Thread sp = new Thread(new Statics());
        sp.start();
        sp.join();
    }

    @Test
    public void testTimeout(){

    }

//    public AtomicInteger pcount = new AtomicInteger(0);
    @Test
    public void performanceCompare() throws Exception{

    }

    class Statics implements Runnable{
        @Override
        public void run() {
            while (true) {
                System.out.println("consumer stat:" + hashMap);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable{
        private IQueue<Integer> q;
        boolean start = true;
        private int id;
        private long sleep;
        public Consumer(IQueue q, int id, long sleep){
            this.q = q;
            this.id = id;
            this.sleep = sleep;
        }
        @Override
        public void run() {
            try {
                while(start) {
                    Integer data = q.receiveMessage();
                    System.out.println("-----recv "+id+","+data);
                    hashMap.put(id,hashMap.get(id)+1);
                    Thread.sleep(sleep);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Producer implements Runnable{
        private IQueue<Integer> q;
        boolean start = true;
        private int id;
        private long sleep;
        public Producer(IQueue q, int id, long sleep){
            this.q = q;
            this.id = id;
            this.sleep = sleep;
        }
        @Override
        public void run() {
            try {
                while(start) {
                    int data = i.incrementAndGet();
                    q.sendMessage(data);
                    System.out.println("send "+id+","+data+",size "+q.getCount());
                    Thread.sleep(sleep);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



} 
