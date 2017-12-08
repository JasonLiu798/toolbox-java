package com.atjl.retry.domain.gen;

import java.io.Serializable;
import java.util.Date;

public class TsProcessLog implements Serializable {
    private Long dataProcessId;

    private String serviceKey;

    private Long totalCount;

    private Long totalPage;

    private Long addCount;

    private Long updCount;

    private Long failCount;

    private Long noNeedUpdCount;

    private Long failPageCount;

    private Long unknownFailCount;

    private Long totalCost;

    private Date crtTm;

    private Date updTm;

    private String processEnd;

    private String success;

    private String processDetail;

    private static final long serialVersionUID = 1L;

    public Long getDataProcessId() {
        return dataProcessId;
    }

    public void setDataProcessId(Long dataProcessId) {
        this.dataProcessId = dataProcessId;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey == null ? null : serviceKey.trim();
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getAddCount() {
        return addCount;
    }

    public void setAddCount(Long addCount) {
        this.addCount = addCount;
    }

    public Long getUpdCount() {
        return updCount;
    }

    public void setUpdCount(Long updCount) {
        this.updCount = updCount;
    }

    public Long getFailCount() {
        return failCount;
    }

    public void setFailCount(Long failCount) {
        this.failCount = failCount;
    }

    public Long getNoNeedUpdCount() {
        return noNeedUpdCount;
    }

    public void setNoNeedUpdCount(Long noNeedUpdCount) {
        this.noNeedUpdCount = noNeedUpdCount;
    }

    public Long getFailPageCount() {
        return failPageCount;
    }

    public void setFailPageCount(Long failPageCount) {
        this.failPageCount = failPageCount;
    }

    public Long getUnknownFailCount() {
        return unknownFailCount;
    }

    public void setUnknownFailCount(Long unknownFailCount) {
        this.unknownFailCount = unknownFailCount;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Date getCrtTm() {
        return crtTm;
    }

    public void setCrtTm(Date crtTm) {
        this.crtTm = crtTm;
    }

    public Date getUpdTm() {
        return updTm;
    }

    public void setUpdTm(Date updTm) {
        this.updTm = updTm;
    }

    public String getProcessEnd() {
        return processEnd;
    }

    public void setProcessEnd(String processEnd) {
        this.processEnd = processEnd == null ? null : processEnd.trim();
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success == null ? null : success.trim();
    }

    public String getProcessDetail() {
        return processDetail;
    }

    public void setProcessDetail(String processDetail) {
        this.processDetail = processDetail == null ? null : processDetail.trim();
    }
}