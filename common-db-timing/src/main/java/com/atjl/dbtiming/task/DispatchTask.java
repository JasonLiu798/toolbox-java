package com.atjl.dbtiming.task;

import com.atjl.dbtiming.core.WaitDispatchQueue;
import com.atjl.dbtiming.core.WaitExecuteQueue;
import com.atjl.dbtiming.core.TimingContext;
import com.atjl.dbtiming.domain.biz.QueueWaitTask;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.thread.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 调度线程
 * 定时被SchedualExecutor唤醒，从 WaitDispatchQueue 获取任务，判断是否到达执行时间
 */
public class DispatchTask extends BaseTask {

    private static Logger logger = LoggerFactory.getLogger(DispatchTask.class);

    public void bizRun() {
        long st = System.currentTimeMillis();
        boolean gotTimeUpTask = true;
        while (gotTimeUpTask) {
            QueueWaitTask pt = WaitDispatchQueue.getInstence().peek();
            if (pt == null) {
                logger.info("empty queue");
                break;
            }
            logger.info("peek task {}", JSONFastJsonUtil.objectToJson(pt));
            Date d = new Date();
            long nowTsMs = 10;//d.getTime();
            if (nowTsMs >= pt.getNextExecuteTs()) {
                long diff = nowTsMs - pt.getNextExecuteTs();
                logger.info("diff {}", diff);
                //是否超过 阈值
                if (diff > TimingContext.DROP_DIFF) {
                    if (pt.isDiffTooBigDrop()) {
                        QueueWaitTask t = WaitDispatchQueue.getInstence().poll();
                        logger.info("task diff too big,dropped {}", JSONFastJsonUtil.objectToJson(t));
                    }
                    gotTimeUpTask = true;
                    continue;
                }

                //时间到
                QueueWaitTask t = WaitDispatchQueue.getInstence().poll();
                if (t != null) {
                    gotTimeUpTask = true;
                    WaitExecuteQueue.getInstence().offer(t);//放入执行队列
                } else {
                    gotTimeUpTask = false;
                }
            } else {
                logger.info("time not reach");
                gotTimeUpTask = false;
            }
            long cost = System.currentTimeMillis() - st;
            logger.info("dispatch task cost {}", cost);
        }


    }
}
