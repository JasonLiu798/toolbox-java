package com.atjl.util.thread;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.reflect.ReflectClassUtil;
import com.atjl.util.thread.domain.ThreadPoolStatus;
import com.atjl.util.thread.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 */
public class ThreadPoolManager {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolManager.class);

    private static ConcurrentHashMap<String, ThreadManager> execs = new ConcurrentHashMap();


    public static synchronized void init(List<String> paramList) {
        /**
         * PoolName,PoolType,corePoolSize,maxPoolSize,keepAliveTime,maxTaskCount
         * 指定参数的
         * poolA,UD,5,10,200000,30000
         * 固定大小
         * poolB,Fix,20
         */
        if (CollectionUtil.isEmpty(paramList)) {
            logger.info("init pool manager fail,param empty");
            return;
        }
        //todo 更详细的校验

        logger.info("init pool manager use param:{}", paramList);
        for (String param : paramList) {
            if (!createCheck(param)) {
                logger.warn("init pool,param invalid {}", param);
                continue;
            }

            ThreadManager execFixed = initOne(param);
            if (execFixed != null) {
                execs.put(ThreadInnerUtil.getName(param), execFixed);
            }
            logger.info("pool {} has been created and add to the map", param);
        }
        logger.info("init pool manager finish");
    }


    public static void execute(String poolName, BaseTask runnable) {
        try {
            execThrowException(poolName, runnable);
        } catch (Exception e) {
            logger.error("pool {} exec fail {}", poolName, e);
        }
    }

    public static void execThrowException(String poolName, BaseTask runnable) {
        ThreadManager tm = exist(poolName);
        tm.execute(runnable);
    }

    public static Future submit(String poolName, BaseTask runnable) {
        try {
            return submitThrowException(poolName, runnable);
        } catch (Exception e) {
            logger.error("pool {} submit fail {}", poolName, e);
        }
        return null;
    }

    public static Future submitThrowException(String poolName, BaseTask runnable) {
        ThreadManager tm = exist(poolName);
        return tm.submit(runnable);
    }


    private static boolean createCheck(String param) {
        if (!ThreadInnerUtil.validParam(param)) {
            logger.warn("init pool,param invalid {}", param);
            return false;
        }
        String threadPoolName = ThreadInnerUtil.getName(param);//name
        if (execs.contains(threadPoolName)) {
            logger.warn("init pool,duplicate thread pool {}", threadPoolName);
            return false;
        }
        return true;
    }

    private static ThreadManager exist(String poolName) {
        ThreadManager es = execs.get(poolName);
        if (es == null) {
            throw new NullPointerException("pool not exit,name " + poolName);
        }
        return es;
    }

    public static ThreadManager getPool(String poolName) {
        return execs.get(poolName);
    }

    private static ThreadManager initOne(String param) {
        String[] params = param.split(ThreadConstant.PARAM_SEP);
        ExecutorService execFixed = null;
        switch (params[1]) {
            case ThreadConstant.BUFPOOL_TYPE_USERDEFINE:
                //corePoolSize,maxPoolSize,keepAliveTime,maxTaskCount
                //PoolName,UD,12,12,30000,200000
                execFixed = new ThreadPoolExecutor(
                        Integer.parseInt(params[2]),//corePoolSize	5
                        Integer.parseInt(params[3]),//maximumPoolSize	10
                        Long.parseLong(params[4]),//maxTasksCount	200000
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(Integer.parseInt(params[5]))
                );

                break;
            case ThreadConstant.BUFPOOL_TYPE_FIX:
                //PoolName,Fix,123
                execFixed = Executors.newFixedThreadPool(Integer.parseInt(params[2]));
                break;
            default:
                logger.warn("unknown type {}", params[1]);
        }
        if (execFixed != null) {
            return new ThreadManager(params[1], execFixed);
        }
        return null;
    }


    /**
     * dynamic add pool
     */
    public static ThreadManager createPool(String name, String param) {
        if (!createCheck(param)) {
            return null;
        }
        ThreadManager tm = initOne(param);
        if (tm != null) {
            execs.put(name, tm);
        }
        return tm;
    }


    /**
     * thread pool status
     */
    public static List<ThreadPoolStatus> getStatus() {
        List<ThreadPoolStatus> statusList = new ArrayList<>();
        for (Map.Entry<String, ThreadManager> entry : execs.entrySet()) {
            ThreadPoolStatus status = new ThreadPoolStatus();
            status.setName(entry.getKey());
            if (entry.getValue() != null) {
                status.setNotNull(true);
            } else {
                status.setNotNull(false);
            }

            ThreadManager tm = entry.getValue();
            ExecutorService es = tm.getExecutorService();
            if (ReflectClassUtil.chkAImplementB(es, ThreadPoolExecutor.class)) {
                ThreadPoolExecutor t = (ThreadPoolExecutor) es;
                status.setActiveCount(t.getActiveCount());
                status.setCompletedTaskCount(t.getCompletedTaskCount());
                status.setCorePoolSize(t.getCorePoolSize());
                status.setLargestPoolSize(t.getLargestPoolSize());
                status.setPoolSize(t.getPoolSize());
                status.setTaskCount(t.getTaskCount());

                BlockingQueue q = t.getQueue();
                if (q != null) {
                    status.setQueueSize(q.size());
                }
            }
            status.setRunningThreadStatuses(tm.getStatus());
            statusList.add(status);
        }
        return statusList;
    }


    /**
     * shutdown call before exit
     */
    public static synchronized void shutdown() {
        logger.info("start destryo pools");
        if (CollectionUtil.isEmpty(execs)) {
            return;
        }
        for (Iterator it = execs.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            ExecutorService exec = (ExecutorService) entry.getValue();
            exec.shutdownNow();
        }
    }
}
