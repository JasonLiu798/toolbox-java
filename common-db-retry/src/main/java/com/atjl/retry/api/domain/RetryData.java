package com.atjl.retry.api.domain;


public class RetryData {

    /**
     * 重试前 设值
     */
    private String id;//主键
    private Long retriedCnt;//已经尝试次数，查询时需置入
    private Long lastRetriedTs;//上次重试时间

    /**
     * 重试完毕后设值
     */
    private String res;//结果内容
    private String failReason;//失败原因

    public void setId(String id) {
        this.id = id;
    }

    public void setLastRetriedTs(Long lastRetriedTs) {
        this.lastRetriedTs = lastRetriedTs;
    }

    public void setRetriedCnt(Long retriedCnt) {
        this.retriedCnt = retriedCnt;
    }

    public String getId() {
        return id;
    }

    public Long getRetriedCnt() {
        return retriedCnt;
    }

    public Long getLastRetriedTs() {
        return lastRetriedTs;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
