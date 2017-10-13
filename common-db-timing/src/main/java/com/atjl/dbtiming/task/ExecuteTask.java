package com.atjl.dbtiming.task;


import com.atjl.dbtiming.core.WaitExecuteQueue;
import com.atjl.dbtiming.domain.biz.QueueWaitTask;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.thread.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行线程
 */
public class ExecuteTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteTask.class);
    private boolean running;

    @Override
    protected void bizRun() {
        running = true;
        while (running) {
            QueueWaitTask t;
            try {
                t = WaitExecuteQueue.getInstence().take();
                logger.info("get task {}", JSONFastJsonUtil.objectToJson(t));
            } catch (InterruptedException e) {
                logger.error("{}", e);
                continue;
            }
            //非加锁检查

            //加锁

            //执行

            //是否需要继续执行
        }
    }
}
