package com.atjl.logdb.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 操作日志，查询条件
 * 表 ts_op_log
 */
@ApiModel
public class OpLogReq implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int DFT_PAGE_SIZE = 10;

    @ApiModelProperty(value = "查询页数", required = true)
    public Integer currentPage = 1;
    @ApiModelProperty(value = "页大小", required = true)
    public Integer pageSize = DFT_PAGE_SIZE;

    @ApiModelProperty(value = "操作类型", required = false)
    private String opType;
    @ApiModelProperty(value = "操作模块", required = false)
    private String opModule;
    @ApiModelProperty(value = "操作人工号", required = false)
    private String empNum;
    @ApiModelProperty(value = "操作人姓名", required = false)
    private String name;
    @ApiModelProperty(value = "日志级别", required = false)
    private String lv;
    @ApiModelProperty(value = "开始时间", required = false)
    private Long startTm;
    @ApiModelProperty(value = "结束时间", required = false)
    private Long endTm;
    @ApiModelProperty(value = "排序方式", required = false, hidden = true)
    private String orderByClause = "OP_TM desc";

    public OpLogReq() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize == null || pageSize <= 0 ? DFT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentIndex() {
        return getCurrentPage() == null || getCurrentPage() <= 0 ? 0 : (getCurrentPage() - 1) * getPageSize();
    }

    public String getOpModule() {
        return opModule;
    }

    public void setOpModule(String opModule) {
        this.opModule = opModule;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartTm() {
        return startTm;
    }

    public void setStartTm(Long startTm) {
        this.startTm = startTm;
    }

    public Long getEndTm() {
        return endTm;
    }

    public void setEndTm(Long endTm) {
        this.endTm = endTm;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

}
