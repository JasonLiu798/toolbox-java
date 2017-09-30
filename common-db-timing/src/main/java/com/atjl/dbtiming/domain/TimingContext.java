package com.atjl.dbtiming.domain;

import com.atjl.dbtiming.domain.biz.TimingManagerStatusDto;
import com.atjl.dbtiming.task.BaseTimingTask;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atjl.dbtiming.domain.biz.TimingConstant;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * timing core context
 * taskCount for count task in pool
 * taskMap for taskid-task object
 * PS:inner task id must below zero
 */
public class TimingContext {
    private static Logger LOG = LoggerFactory.getLogger(TimingContext.class);

    /**
     * clear
     */
    public static void clearAll() {
        LogDbUtil.warn(TimingConstant.MODULE_TIMING, "before clear timing context,CNT:" + getRunningTaskCnt() + ",taskMap:" + JSONFastJsonUtil.objectToJson(taskMap));
        taskCount = new AtomicInteger();
        executorService = null;
        taskMap = new ConcurrentHashMap<>();
    }


    /**
     * ################### for monitor ######################
     */
    public static String MID = "DFTMID";
    /**
     * running task count
     */
    private static AtomicInteger taskCount = new AtomicInteger();

    /**
     * incr /desc running task count
     */
    public static void incrementTaskCnt() {
        taskCount.incrementAndGet();
    }

    public static void decrementTaskCnt() {
        taskCount.decrementAndGet();
    }

    public static int getRunningTaskCnt() {
        return taskCount.get();
    }


    /**
     * #################### core struct ######################
     */
    /**
     * core schedule pool
     */
    public static ScheduledExecutorService executorService;

    /**
     * build schedule pool
     *
     * @param poolsize
     */
    public static synchronized boolean buildTaskPool(int poolsize) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("init task pool,size {}", poolsize);
        }
        if (executorService == null) {
            executorService = Executors.newScheduledThreadPool(poolsize);
        }
        if (executorService == null) {
            return false;
        }
        return true;
    }

    /**
     * destroy pool
     *
     * @return
     */
    public static synchronized boolean deatroyPool() {
        List<Runnable> rlist = executorService.shutdownNow();
        LogDbUtil.error(TimingConstant.MODULE_TIMING, "manual shutdown task", JSONFastJsonUtil.objectToJson(rlist));
        return true;
    }

    /**
     * cancel task
     *
     * @param tid task id
     * @return
     */
    public static synchronized boolean cancleTask(Long tid) {
        ScheduledFuture future = getFuture(tid);
        if (future == null) {
            return false;
        }
        boolean cancelRes = future.cancel(true);
        if (cancelRes) {
            TimingContext.removeTask(tid);
        } else {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "call task future cancel fail,tid " + tid);
            DEAD_THREAD_NOT_CLEAR.incrementAndGet();
        }
        return cancelRes;
    }

    //monitor task cancel fail,still in pool counter
    public static AtomicInteger DEAD_THREAD_NOT_CLEAR = new AtomicInteger();

    public static void forceClearMap(Long tid) {
        TimingContext.removeTask(tid);
    }


    /**
     * for get task
     */
    private static Map<Long, BaseTimingTask> taskMap = new ConcurrentHashMap<>();

    /**
     * task exist in map
     *
     * @param tid
     * @return
     */
    public static boolean taskExist(Long tid) {
        return taskMap.containsKey(tid);
    }

    /**
     * add task to map
     *
     * @param tid
     * @param task
     */
    public static void addTask(Long tid, BaseTimingTask task) {
        taskMap.put(tid, task);
    }

    /**
     * get task from map,by id
     *
     * @param tid
     * @return
     */
    public static BaseTimingTask getTask(Long tid) {
        return taskMap.get(tid);
    }

    /**
     * remove task from map
     *
     * @param tid
     * @return
     */
    public static BaseTimingTask removeTask(Long tid) {
        BaseTimingTask t = null;
        if (tid != null) {
            t = taskMap.remove(tid);
        } else {
            LOG.error("remove task fail tid null");
        }
        decrementTaskCnt();
        return t;
    }

    /**
     * get futrue for cancel
     *
     * @param tid
     * @return
     */
    public static ScheduledFuture getFuture(Long tid) {
        BaseTimingTask t = getTask(tid);
        if (t != null) {
            return t.getFuture();
        }
        return null;
    }

    /**
     * get tasks
     *
     * @return
     */
    public static Set<Long> getTasks() {
        return taskMap.keySet();
    }

    /**
     * get task list
     *
     * @return
     */
    public static List<Long> getTaskList() {
        Set<Long> tasks = taskMap.keySet();
        if (CollectionUtil.isEmpty(tasks)) {
            return null;
        }
        List<Long> res = new ArrayList<>(tasks.size());
        for (Long t : tasks) {
            res.add(t);
        }
        return res;
    }

    /**
     * get biz tasks
     *
     * @return
     */
    public static List<Long> getTaskListBiz() {
        if (!CollectionUtil.isEmpty(taskMap.keySet())) {
            List<Long> tasks = new LinkedList<>();
            for (Long tid : taskMap.keySet()) {
                if (tid >= 0) {
                    tasks.add(tid);
                }
            }
            return tasks;
        }
        return null;
    }


    /**
     * monitor message for web
     *
     * @return
     */
    public static TimingManagerStatusDto getTaskStatusDto() {
        TimingManagerStatusDto timingManagerStatusDto = new TimingManagerStatusDto();
        timingManagerStatusDto.setTaskCount(taskCount.intValue());
        timingManagerStatusDto.setMid(MID);
        timingManagerStatusDto.setDeadCnt(DEAD_THREAD_NOT_CLEAR.intValue());

        if (CollectionUtil.isEmpty(taskMap)) {
            return timingManagerStatusDto;
        }

        for (Map.Entry<Long, BaseTimingTask> entry : taskMap.entrySet()) {
            timingManagerStatusDto.addTaskStatus(entry.getValue());
        }

        return timingManagerStatusDto;
    }

    /**
     * pool status fro console
     *
     * @return
     */
    public static String getStatusFmt() {
        return getTaskStatusDto().fmt();
    }

    /**
     * pool status fro outer
     *
     * @return
     */
    public static TimingManagerStatusDto getStatusRaw() {
        return getTaskStatusDto();
    }


}
