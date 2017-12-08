package com.atjl.util.thread;

import com.atjl.common.api.resp.ResponseDataDto;
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
        logger.info("init pool manager use param:{}", paramList);
        for (String param : paramList) {
            if (!createCheck(param)) {
                logger.warn("init pool,param invalid {}", param);
                continue;
            }

            ThreadManager execFixed = initOne(param);
            if (execFixed != null) {
                String name = ThreadInnerUtil.getName(param);
                execs.put(name, execFixed);
                logger.info("pool created,name {},param {}", name, param);
            } else {
                logger.error("pool {} init fail", param);
            }
        }
        logger.info("init pool manager finish");
    }

    /**
     * dynamic add pool
     */
    public static ThreadManager addPool(String param) {
        if (!createCheck(param)) {
            return null;
        }
        String name = ThreadInnerUtil.getName(param);
        ThreadManager tm = initOne(param);
        if (tm != null) {
            execs.put(name, tm);
        }
        return tm;
    }


    public static void execute(String poolName, BaseTask runnable) {
        try {
            execThrowException(poolName, runnable);
        } catch (Exception e) {
            logger.error("pool {} exec fail {}", poolName, e);
        }
    }

    public static void execThrowException(String poolName, BaseTask runnable) {
        ThreadManager tm = existAndThrow(poolName);
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
        ThreadManager tm = existAndThrow(poolName);
        return tm.submit(runnable);
    }


    private static boolean createCheck(String param) {
        if (!ThreadInnerUtil.validParam(param)) {
            logger.warn("init pool,param invalid {}", param);
            return false;
        }
        String threadPoolName = ThreadInnerUtil.getName(param);//name
        if (execs.containsKey(threadPoolName)) {
            logger.warn("init pool,duplicate thread pool {}", threadPoolName);
            return false;
        }
        return true;
    }

    private static ThreadManager existAndThrow(String poolName) {
        ThreadManager es = execs.get(poolName);
        if (es == null) {
            throw new NullPointerException("pool not exit,name " + poolName);
        }
        return es;
    }


    public static ThreadManager getPool(String poolName) {
        return execs.get(poolName);
    }

    /**
     * @param param
     * @return
     */
    private static ThreadManager initOne(String param) {
        Class clz = ThreadInnerUtil.getType(param);
        if (clz == null) {
            return null;
        }
        Object m = ReflectClassUtil.newInstance(clz, CollectionUtil.newArr(String.class, String.class), CollectionUtil.newArr(ThreadInnerUtil.getName(param), param));
        if (m == null) {
            return null;
        }
        ThreadManager tm = (ThreadManager) m;
        if (!tm.validParam()) {
            return null;
        }
        if (!tm.initFromParam(param)) {
            return null;
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
            status.setParam(tm.getParam());
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
     * 使用新参数重建线程池
     * sync 同步方法
     *
     * @param param 新参数
     * @return 结果
     */
    public static ResponseDataDto rebuild(String param) {
        if (!ThreadInnerUtil.validParam(param)) {
            return ResponseDataDto.buildFail(1000, param + " fromat error");
        }

        String poolName = ThreadInnerUtil.getName(param);
        if (!execs.containsKey(poolName)) {
            return ResponseDataDto.buildFail(1000, poolName + "not exist");
        }
        ThreadManager threadPoolManager = execs.get(poolName);
        threadPoolManager.setParam(param);
        try {
            return threadPoolManager.rebuild();
        } catch (Exception e) {
            return ResponseDataDto.buildFail(1001, param + "rebuild fail,excep " + e);
        }
    }

    /**
     * shutdown call before exit
     */
    public static synchronized void shutdown() {
        logger.info("start destryo pools");
        if (CollectionUtil.isEmpty(execs)) {
            return;
        }
        for (Map.Entry<String, ThreadManager> entry : execs.entrySet()) {
            entry.getValue().destroy();
            try {
                ExecutorService exec = (ExecutorService) entry.getValue();
                exec.shutdown();
            } catch (Exception e) {
                logger.error("shutdown fail {}", e);
            }
        }
    }
}
