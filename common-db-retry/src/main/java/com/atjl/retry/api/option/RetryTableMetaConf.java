package com.atjl.retry.api.option;

import com.atjl.common.constant.CommonConstant;
import com.atjl.util.collection.CollectionUtil;

import java.util.Set;

/**
 * 表 基础信息
 */
public class RetryTableMetaConf {
    /**
     * 必填
     */
    private String tab;//重试数据所在表
    private String idCol;//主键 列名
    private String retryCountCol;//尝试次数列名
    private String lastRetriedTsCol;//重试时间列名
    private String resCol;//结果列名
    private String failReasonCol;//失败原因列名
    /**
     * 需要重试字段值
     */
    private String needRetryMark = CommonConstant.NO;

    private Set<String> dataCols;//表内其他需要查询的字段，会被放入 dataContext的data内

    //其他条件字段
    private String otherConds;
    //排序字段，默认按主键排序
    private String otherOrderByClause;


    public String getOtherConds() {
        return otherConds;
    }

    public void setOtherConds(String otherConds) {
        this.otherConds = otherConds;
    }

    public boolean getHasUserDefine() {
        return !CollectionUtil.isEmpty(dataCols);
    }

    public String getLastRetriedTsCol() {
        return lastRetriedTsCol;
    }

    public Set<String> getDataCols() {
        return dataCols;
    }

    public void setDataCols(Set<String> dataCols) {
        this.dataCols = dataCols;
    }

    public void setLastRetriedTsCol(String lastRetriedTsCol) {
        this.lastRetriedTsCol = lastRetriedTsCol;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getIdCol() {
        return idCol;
    }

    public void setIdCol(String idCol) {
        this.idCol = idCol;
    }

    public String getRetryCountCol() {
        return retryCountCol;
    }

    public void setRetryCountCol(String retryCountCol) {
        this.retryCountCol = retryCountCol;
    }

    public String getOtherOrderByClause() {
        return otherOrderByClause;
    }

    public void setOtherOrderByClause(String otherOrderByClause) {
        this.otherOrderByClause = otherOrderByClause;
    }

    public String getNeedRetryMark() {
        return needRetryMark;
    }

    public void setNeedRetryMark(String needRetryMark) {
        this.needRetryMark = needRetryMark;
    }

    public String getResCol() {
        return resCol;
    }

    public void setResCol(String resCol) {
        this.resCol = resCol;
    }

    public String getFailReasonCol() {
        return failReasonCol;
    }

    public void setFailReasonCol(String failReasonCol) {
        this.failReasonCol = failReasonCol;
    }
}
