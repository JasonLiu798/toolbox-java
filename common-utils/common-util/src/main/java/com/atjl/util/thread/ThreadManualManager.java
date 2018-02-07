package com.atjl.util.thread;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用参数  corePoolSize,maxPoolSize,keepAliveTime,maxTaskCount 初始化
 */
public class ThreadManualManager extends ThreadManager {
    public ThreadManualManager(String name, String param) {
        super(name, param);
    }

    @Override
    public boolean initFromParam(String param) {
        String[] params = param.split(ThreadConstant.PARAM_SEP);
        //corePoolSize,maxPoolSize,keepAliveTime,maxTaskCount
        //PoolName,UD,12,12,30000,200000
        /**
         * int corePoolSize, 12
         int maximumPoolSize, 12
         long keepAliveTime, 30*1000
         TimeUnit unit,
         BlockingQueue<Runnable> workQueue ,
         */
        ExecutorService execFixed = new ThreadPoolExecutor(
                Integer.parseInt(params[2]),//corePoolSize	5
                Integer.parseInt(params[3]),//maximumPoolSize	10
                Long.parseLong(params[4]),//keepAliveTime	200000
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(Integer.parseInt(params[5])),//queueSize
                new NamedThreadFactory(params[0])//thread Factory
        );
        if (execFixed == null) {
            return false;
        }
        this.executorService = execFixed;
        return true;
    }


    static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            String filterName = StringCheckUtil.isEmpty(name) ? "P" + StringUtil.getUUID() : name;
            namePrefix = filterName +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }


    @Override
    public boolean validParam() {
        if (!ThreadInnerUtil.validParam(param)) {
            return false;
        }
        String[] arr = this.param.split(ThreadInnerUtil.PARAM_SEP);
        if (arr.length != 6) {
            return false;
        }
        return true;
    }

}
