package com.jason798.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * todo:add multi thread pool manage
 */
public final class ExecutorUtil {

    static final Logger logger = LoggerFactory.getLogger(ExecutorUtil.class);

    static int DFT_IO_THREAD_NUM = 8;
    static int DFT_IO_THREAD_MAX_NUM = 16;
    static int DFT_IO_THREAD_KEEPALIVE_TIME = 1000;
    static int DFT_IO_BLOCKQUEUED_SIZE = 10;

    private ExecutorService executor;

    private static final ThreadPoolExecutor threadPoolExecutorForIO = new ThreadPoolExecutor(DFT_IO_THREAD_NUM, DFT_IO_THREAD_NUM, DFT_IO_THREAD_KEEPALIVE_TIME, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(DFT_IO_BLOCKQUEUED_SIZE), new DefaultThreadFactory(), new AbortPolicy());

    /**
     * singleton
     */
    private static class HandlerExecutorsHolder {
        private static ExecutorUtil handlerExecutor = new ExecutorUtil();
    }


    public void init() {
        init(DFT_IO_THREAD_NUM,DFT_IO_THREAD_MAX_NUM,DFT_IO_THREAD_KEEPALIVE_TIME,DFT_IO_BLOCKQUEUED_SIZE,new DefaultThreadFactory());
    }

    public void init(int size, int maxsize, int threadLiveTime, int queueSize,ThreadFactory tf) {
        new ThreadPoolExecutor(size, maxsize, threadLiveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(queueSize), tf, new AbortPolicy());
    }

    private ExecutorUtil() {
        executor = Executors.newFixedThreadPool(10);
    }

    public static ExecutorUtil getInstance() {
        return HandlerExecutorsHolder.handlerExecutor;
    }

    public void execute(Runnable command) {
        executor.execute(command);
    }

    public void shutdown() {
        executor.shutdown();
        threadPoolExecutorForIO.shutdown();
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "threadpool-" + poolNumber.getAndIncrement() + "-thread-";
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    public static interface Handle<T> {
        public T handle();
    }

    public static final <T> T getResultInLimitTime(Callable<T> callable, long timeout, Handle<T> timeoutHandle) {
        ThreadPoolExecutor pool = threadPoolExecutorForIO;
        Future<T> future = pool.submit(callable);
        T result = null;

        try {
            result = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            String message = callable.getClass().getName();
            logger.error("timeout message|{}, e.message{}", message, e.getMessage(), e);
            e.printStackTrace();
            future.cancel(true);
            result = timeoutHandle.handle();
        } catch (Exception e) {
            logger.error("exception|{}", e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

}

