package com.jason798.queue.impl;

import com.jason798.queue.IQueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * concurrent linke queue add lock condition
 */
public class ConcurrentLinkedBlockingQueue<T> implements IQueue<T> {

    /**
     * lock area
     */
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();

    /**
     * queue
     */
    private ConcurrentLinkedQueue<T> linkq;

    /**
     * counter
     */
    private AtomicInteger count;

    /**
     * max size ,default 65535
     */
    private int maxSize = 65535;

    public ConcurrentLinkedBlockingQueue() {
        init();
    }

    public ConcurrentLinkedBlockingQueue(int maxSize) {
        init();
        this.maxSize = maxSize;
    }

    private void init() {
        linkq = new ConcurrentLinkedQueue<>();
        count = new AtomicInteger(0);
    }

    /**
     * send one msg
     *
     * @param message msg to send
     * @throws InterruptedException interrupted exception
     */
    public void sendMessage(T message) throws InterruptedException {
        if (message != null) {
            lock.lock();
            try {
                //wait until buffer empty
                while (count.get() == maxSize) {
                    full.await();
                }
                linkq.add(message);
                count.incrementAndGet();
                empty.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * receive msg
     *
     * @return Object polled  object
     * @throws InterruptedException interrupted exception
     */
    public T receiveMessage() throws InterruptedException {
        T message;
        lock.lock();
        try {
            while (count.get() == 0) {
                empty.await();
                Thread.sleep(100);
            }
            message = linkq.poll();
            count.decrementAndGet();
            // awake other producer
            full.signal();
            return message;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T receiveMessage(long mstime) throws InterruptedException {
        T message;
        lock.lock();
        try {
            while (count.get() == 0) {
                empty.await(mstime, TimeUnit.MILLISECONDS);
                Thread.sleep(100);
            }
            if (linkq.size() == 0) {
                return null;
            }
            message = linkq.poll();
            count.decrementAndGet();
            // awake other producer
            full.signal();
            return message;
        } finally {
            lock.unlock();
        }
    }

    /**
     * get now size
     *
     * @return int queue size
     */
    public int getCount() {
        return count.get();
    }


    /**
     * chk producer buffer
     *
     * @return boolean is queue full
     */
    public boolean ifMaxCount() {
        return (maxSize - count.get()) < 10;
    }


}

