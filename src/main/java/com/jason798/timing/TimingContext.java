package com.jason798.timing;

import com.jason798.character.StringCheckUtil;
import com.jason798.collection.CollectionUtil;
import com.jason798.timing.entity.TimingManagerStatusDto;
import com.jason798.timing.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class TimingContext {
    private static Logger LOG = LoggerFactory.getLogger(TimingContext.class);
    /**
     * ################### for monitor ######################
     */
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

    public int getRunningTaskCnt() {
        return taskCount.get();
    }


    /**
     * #################### core struct ######################
     */
    /**
     * core schedule pool
     */
    static ScheduledExecutorService executorService;

    /**
     * build schedule pool
     *
     * @param poolsize
     */
    public static synchronized boolean buildTaskPool(int poolsize) {
        executorService = Executors.newScheduledThreadPool(poolsize);
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
        executorService.shutdown();
        return true;
    }

    /**
     * cancel task
     *
     * @param tid task id
     * @return
     */
    public static synchronized boolean cancleTask(String tid) {
        ScheduledFuture future = getFuture(tid);
        if (future == null) {
            return false;
        }
        boolean cancelRes = future.cancel(true);
        if (cancelRes) {
            TimingContext.removeTask(tid);
        }else{
            LOG.error("cancel {} res false",tid);
        }
        return cancelRes;
    }


    /**
     * for get task
     */
    private static Map<String, BaseTask> taskMap = new ConcurrentHashMap<>();

    public static boolean taskExist(String tid) {
        return taskMap.containsKey(tid);
    }

    public static void addTask(String tid, BaseTask task) {
        taskMap.put(tid, task);
    }

    public static BaseTask getTask(String tid) {
        return taskMap.get(tid);
    }

    /**
     * remove task from map
     * @param tid
     * @return
     */
    public static BaseTask removeTask(String tid) {
        BaseTask t = null;
        if (!StringCheckUtil.isEmpty(tid)) {
            t = taskMap.remove(tid);
        }else{
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
    public static ScheduledFuture getFuture(String tid) {
        BaseTask t = getTask(tid);
        if (t != null) {
            return t.getFuture();
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
        if (CollectionUtil.isEmpty(taskMap)) {
            return timingManagerStatusDto;
        }
        for (Map.Entry<String, BaseTask> entry : taskMap.entrySet()) {
            timingManagerStatusDto.addTaskStatus(entry.getValue());
        }
        return timingManagerStatusDto;
    }

    /**
     * monitor message for console
     *
     * @return
     */
    public static String getStatusFmt() {
        TimingManagerStatusDto timingManagerStatusDto = getTaskStatusDto();
        return timingManagerStatusDto.fmt();
    }

}
