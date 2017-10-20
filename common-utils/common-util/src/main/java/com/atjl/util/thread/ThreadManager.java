package com.atjl.util.thread;

import com.atjl.common.api.resp.ResponseDataDto;
import com.atjl.util.collection.CollectionUtil;
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

/**
 *
 */
public abstract class ThreadManager {
    private static Logger logger = LoggerFactory.getLogger(ThreadManager.class);

    protected String name;
    protected String param;

    protected ExecutorService executorService;

    /**
     * !!! 谨防内存泄露 !!!
     * 潜在问题：future 提前停止的线程，未被移除
     */
    protected Map<String, BaseTask> runningTaskMap = new ConcurrentHashMap<>();

    /**
     * 是否执行
     */
    protected boolean start = false;

    public ThreadManager(String name, String param) {
        this.name = name;
        this.param = param;
    }

    public abstract boolean initFromParam(String param);

    public void beforeAdd(BaseTask t) {
        t.setThreadManager(this);
        runningTaskMap.put(String.valueOf(t.getId()), t);
    }

    public void rmFromMap(String id) {
        runningTaskMap.remove(id);
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

    public abstract boolean validParam();

    /**
     * shutdown
     * 读取新参数
     * 重新生成 pool
     */
    public synchronized ResponseDataDto rebuild() {
        if (!validParam()) {
            return ResponseDataDto.buildFail(1001, "param invalid " + this.param);
        }
        if (!destroy()) {
            return ResponseDataDto.buildFail(1003, "shutdown fail ");
        }
        if (!initFromParam(param)) {
            return ResponseDataDto.buildFail(1002, "rebuild fail");
        }
        return ResponseDataDto.buildOk();
    }


    public boolean destroy() {
        if (executorService != null) {
            executorService.shutdown();
            runningTaskMap.clear();
            return true;
        }
        return false;
    }

    /**
     * ################### getter && setter #################
     */
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart() {
        this.start = true;
    }

    public void setStop() {
        this.start = false;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

//    public void setExecutorService(ExecutorService executorService) {
//        this.executorService = executorService;
//    }
}
