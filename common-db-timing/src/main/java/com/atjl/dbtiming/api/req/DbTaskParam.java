package com.atjl.dbtiming.api.req;

import java.io.Serializable;

/**
 * add task param
 */
public class DbTaskParam extends DynamicTaskParam implements Serializable {
    /**
     * ################## 必填 ##################
     */
    /**
     * 主键
     */
    private Long tid;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}
