package com.atjl.util.thread;

import com.atjl.util.common.DateUtil;
import com.atjl.util.net.IPUtil;
import com.atjl.util.queue.IQueue;
import com.atjl.util.thread.task.BaseTask;
import com.atjl.util.thread.task.ReadTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private static Logger LOG = LoggerFactory.getLogger(ThreadManager.class);
    private ExecutorService executorService;

    private int slaveCnt = 1;
    private int maserCnt = 1;

    private Map<String, BaseTask> taskMap = new HashMap<>();

    private static IQueue queue;

    private boolean start = false;
    private boolean poolInited = false;

    private static int id = 0;



    /**
     * spring初始化完毕调用
     */
    private synchronized void init() {
        int poolSize = slaveCnt + maserCnt;
        //queue = QueueManager.getQueue(LogClient.LOG_QUEUE);
        initPool(poolSize);
    }

    /**
     * 开始处理
     */
    public synchronized void startTask() {
        init();
        if (!start) {
            Long ip = null;
            try {
                ip = IPUtil.getLocalIPLong();
            } catch (Exception e) {
                //LogDbUtil.error(LogConstant.MODULE_LOG,"获取本地ip异常", e);
//                LogClient.writeErrorSync(ThreadManager.class.getSimpleName(),"get local ip exception", e);
            }

            ReadTask readTask = new ReadTask(id, queue, ip);
            id++;
            executorService.submit(readTask);
            setStart();
        }
    }

    /**
     * 手动停止
     */
    public synchronized void manualStop() {
        //stop tasks
        for (Map.Entry<String, BaseTask> entry : taskMap.entrySet()) {
            BaseTask task = entry.getValue();
            task.stop();
            LOG.info("task {} stop send", entry.getValue());
        }
        setStop();
        destroyPool();
    }


    public boolean isRunning() {
        return start;
    }

    public void manualClean() {
        destroyPool();
    }

    public boolean isStart() {
        return start;
    }

    public void setStart() {
        this.start = true;
    }

    public void setStop() {
        long ts = DateUtil.getNowTS();
        this.start = false;
    }

    public void initPool(int poolsize) {
        if (poolInited == false) {
            executorService = Executors.newFixedThreadPool(poolsize);
        }
        poolInited = true;
    }

    public void destroyPool() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public void rebuildThreadPool(int poolsize) {
        if (executorService != null) {
            executorService.shutdown();
            initPool(poolsize);
        }
    }

    public void destroy() {

    }
}
