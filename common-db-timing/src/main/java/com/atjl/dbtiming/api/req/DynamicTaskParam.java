package com.atjl.dbtiming.api.req;

import java.io.Serializable;

/**
 * add task param
 * <p>
 * <p>
 * 支持的任务类型：
 * CRON:     cronExpression
 * INTERVAL: delay,interval
 * <p>
 * hasParam：执行时 先放入参数
 * hasCond:执行完毕时，检查条件
 * maxRunCnt>1:执行完毕，检查次数
 */
public class DynamicTaskParam implements Serializable {
    /**
     * ################## 必填 ##################
     */
    /**
     * 唯一代号
     */
    private String tkey;
    /**
     * 任务类型，默认crontab类型
     *
     private TaskType taskType = TaskType.CRON;*/

    /**
     * 配置
     */
    private TaskConf taskConf;

    /**
     * ################ 可选项 ###################
     */
    /**
     * 是否持久化
     * false：不支持的功能（指定次数后，如果服务器中间停止，再次启动已执行次数会丢失
     * <p>
     * private boolean persist = true;
     */

    public String getTkey() {
        return tkey;
    }

    public void setTkey(String tkey) {
        this.tkey = tkey;
    }

//    public TaskType getTaskType() {
//        return taskType;
//    }
//    public void setTaskType(TaskType taskType) {
//        this.taskType = taskType;
//    }

    public TaskConf getTaskConf() {
        return taskConf;
    }

    public void setTaskConf(TaskConf taskConf) {
        this.taskConf = taskConf;
    }
}
