package com.atjl.dbtiming.api.req;

import com.atjl.dbtiming.api.RetCode;
import com.atjl.dbtiming.domain.biz.TimingConstant;
import com.atjl.util.common.DateUtil;

import java.io.Serializable;

/**
 * rexe db task param
 */
public class RexeTaskParam extends AddTaskParam implements Serializable {

    // last manager alive time
    private Long aliveTm;
    // already runned count
    private Long runedCnt = 0L;
    // last start ms
    private Long lastStartMs;
    // last stop ms
    private Long lastStopMs;

    public RexeTaskParam() {
    }

    public static RexeTaskParam buildFailNull() {
        RexeTaskParam p = new RexeTaskParam();
        p.setFailReason(RetCode.DB_TASK_NULL);
        return p;
    }

    public static RexeTaskParam buildFailStopOrInvalid() {
        RexeTaskParam p = new RexeTaskParam();
        p.setFailReason(RetCode.TASK_STOP_OR_INVALID);
        return p;
    }

    public RexeTaskParam enableDbNew(Long runedCnt, Long aliveTm) {
        //this.newTask = true;
        if (aliveTm == null) {
            this.aliveTm = DateUtil.getNowTS() - TimingConstant.NOT_ALIVE_REF*2;
        } else {
            this.aliveTm = aliveTm;
        }
        if (runedCnt == null) {
            this.runedCnt = 0L;
        } else {
            this.runedCnt = runedCnt;
        }
        return this;
    }

    public RexeTaskParam enableNew() {
        super.enableNew();
        this.aliveTm = DateUtil.getNowTS();
        this.runedCnt = 0L;
        return this;
    }

    /**
     * ####################### getter && setter ######################
     */
    public Long getAliveTm() {
        return aliveTm;
    }

    public AddTaskParam setAliveTm(Long aliveTm) {
        this.aliveTm = aliveTm;
        return this;
    }

    public Long getRunedCnt() {
        return runedCnt;
    }

    public AddTaskParam setRunedCnt(Long runedCnt) {
        this.runedCnt = runedCnt;
        return this;
    }

    public Long getLastStartMs() {
        return lastStartMs;
    }

    public void setLastStartMs(Long lastStartMs) {
        this.lastStartMs = lastStartMs;
    }

    public Long getLastStopMs() {
        return lastStopMs;
    }

    public void setLastStopMs(Long lastStopMs) {
        this.lastStopMs = lastStopMs;
    }
}
