package com.atjl.util.thread;

import com.atjl.common.api.LifeCycle;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.thread.domain.ThreadStatus;
import com.atjl.util.thread.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ThreadManager implements LifeCycle {
    private static Logger logger = LoggerFactory.getLogger(ThreadManager.class);

    private String name;

    private ExecutorService executorService;

    /**
     * !!! 谨防内存泄露 !!!
     */
    private Map<String, BaseTask> runningTaskMap = new ConcurrentHashMap<>();

    private boolean start = false;

    public ThreadManager(String name, ExecutorService executorService) {
        this.name = name;
        this.executorService = executorService;
    }


    @Override
    public void init() {
    }

    public void beforeAdd(BaseTask t) {
        t.setThreadManager(this);
        runningTaskMap.put(String.valueOf(t.getId()), t);
    }

    public void rmFromMap(long id) {
        runningTaskMap.remove(String.valueOf(id));
    }


    public void execute(BaseTask t) {
        try {
            executeThrow(t);
        } catch (Exception e) {
            logger.error("pool {} exec fail {}", name, e);
            rmFromMap(t.getId());
        }
    }

    public void executeThrow(BaseTask t) {
        beforeAdd(t);
        executorService.execute(t);
    }

    public Future submit(BaseTask r) {
        try {
            return submitThrow(r);
        } catch (Exception e) {
            logger.error("pool {} submit fail {}", name, e);
        }
        return null;
    }

    public Future submitThrow(BaseTask t) {
        beforeAdd(t);
        return executorService.submit(t);
    }

    public List<ThreadStatus> getStatus() {
        List<ThreadStatus> status = new ArrayList<>();
        if (!CollectionUtil.isEmpty(runningTaskMap)) {
            for (Map.Entry<String, BaseTask> entry : runningTaskMap.entrySet()) {
                ThreadStatus s = new ThreadStatus();
                BaseTask t = entry.getValue();
                s.setId(String.valueOf(t.getId()));
                s.setState(t.getState());
                status.add(s);
            }
        }
        return status;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

//
//    /**
//     * spring初始化完毕调用
//     */
//    private void init() {
////        int poolSize = slaveCnt + maserCnt;
//        //queue = QueueManager.getQueue(LogClient.LOG_QUEUE);
////        initPool(poolSize);
//    }

//    /**
//     * 开始处理
//     */
//    public synchronized void startTask() {
//        init();
//        if (!start) {
//            Long ip = null;
//            try {
//                ip = IPUtil.getLocalIPLong();
//            } catch (Exception e) {
//                //LogDbUtil.error(LogConstant.MODULE_LOG,"获取本地ip异常", e);
////                LogClient.writeErrorSync(ThreadManager.class.getSimpleName(),"get local ip exception", e);
//            }
//
////            ReadTask readTask = new ReadTask(id, queue, ip);
////            id++;
////            executorService.submit(readTask);
////            setStart();
//        }
//    }


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

//    public void initPool(int poolsize) {
//        if (poolInited == false) {
//            executorService = Executors.newFixedThreadPool(poolsize);
//        }
//        poolInited = true;
//    }


    public void rebuildThreadPool() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }


//    /**
//     * 手动停止
//     */
//    public synchronized void manualStop() {
//        //stop tasks
//        for (Map.Entry<String, BaseTask> entry : taskMap.entrySet()) {
//            BaseTask task = entry.getValue();
//            task.stop();
//            logger.info("task {} stop send", entry.getValue());
//        }
//    }

    @Override
    public void destroy() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
