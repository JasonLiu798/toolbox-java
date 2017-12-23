package com.atjl.logdb.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 操作日志实体
 */
@ApiModel
public class OpLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "日志唯一编号", required = true)
    private Long logId;

    @ApiModelProperty(value = "日志级别", example = "I", required = true)
    private String lv;

    @ApiModelProperty(value = "操作时间", required = true)
    private Long opTm;

    @ApiModelProperty(value = "操作模块", required = true)
    private String opModule;

    @ApiModelProperty(value = "操作人ID", required = true)
    private String empNum;
    @ApiModelProperty(value = "操作人姓名", required = true)
    private String userName;
    @ApiModelProperty(value = "用户扩展信息", required = true)
    private String userExtInfo;

    @ApiModelProperty(value = "子模块", required = true)
    private String opChildModule;

    @ApiModelProperty(value = "操作类型", required = true)
    private String opType;
    @ApiModelProperty(value = "操作参数，入参", required = true)
    private String opParam;
    @ApiModelProperty(value = "操作内容", required = true)
    private String opContent;
    @ApiModelProperty(value = "操作人主机名", required = true)
    private String opHostName;
    @ApiModelProperty(value = "操作结果，出参", required = true)
    private String opRes;
    @ApiModelProperty(value = "操作参考信息", required = true)
    private String opRef;

    @ApiModelProperty(value = "操作人IP", required = true)
    private Long opIp;
    @ApiModelProperty(value = "服务IP", required = true)
    private Long serviceIp;
    @ApiModelProperty(value = "操作来源", required = true)
    private String source;
    @ApiModelProperty(value = "操作备注", required = true)
    private String memo;


    @ApiModelProperty(value = "操作耗时", required = true)
    private Long cost;

    @ApiModelProperty(value = "操作开始时间", required = true, hidden = true)
    private Long opStartTm;
    @ApiModelProperty(value = "操作结束时间", required = true, hidden = true)
    private Long opEndTm;

    @ApiModelProperty(value = "操作线程ID", required = true, hidden = true)
    private Long threadId;


    @ApiModelProperty(value = "操作uri", required = true)
    private String uri;

    @ApiModelProperty(value = "user agent", required = true)
    private String userAgent;


    public OpLog() {
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public String getOpRes() {
        return opRes;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public void setOpRes(String opRes) {
        this.opRes = opRes;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public Long getOpStartTm() {
        return opStartTm;
    }

    public void setOpStartTm(Long opStartTm) {
        this.opStartTm = opStartTm;
    }

    public Long getOpEndTm() {
        return opEndTm;
    }

    public void setOpEndTm(Long opEndTm) {
        this.opEndTm = opEndTm;
    }

    public String getUserExtInfo() {
        return userExtInfo;
    }

    public void setUserExtInfo(String userExtInfo) {
        this.userExtInfo = userExtInfo;
    }

    public String getOpHostName() {
        return opHostName;
    }

    public String getOpChildModule() {
        return opChildModule;
    }

    public void setOpChildModule(String opChildModule) {
        this.opChildModule = opChildModule;
    }

    public String getOpRef() {
        return opRef;
    }

    public void setOpRef(String opRef) {
        this.opRef = opRef;
    }

    public void setOpHostName(String opHostName) {
        this.opHostName = opHostName;
    }

    public String getOpModule() {
        return opModule;
    }

    public void setOpModule(String opModule) {
        this.opModule = opModule;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpParam() {
        return opParam;
    }

    public void setOpParam(String opParam) {
        this.opParam = opParam;
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent;
    }

    public Long getOpIp() {
        return opIp;
    }

    public void setOpIp(Long opIp) {
        this.opIp = opIp;
    }

    public Long getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(Long serviceIp) {
        this.serviceIp = serviceIp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getOpTm() {
        return opTm;
    }

    public void setOpTm(Long opTm) {
        this.opTm = opTm;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    @Override
    public String toString() {
        return "OpLog{" +
                "logId=" + logId +
                ", lv='" + lv + '\'' +
                ", opTm=" + opTm +
                ", opModule='" + opModule + '\'' +
                ", empNum='" + empNum + '\'' +
                ", userName='" + userName + '\'' +
                ", userExtInfo='" + userExtInfo + '\'' +
                ", opChildModule='" + opChildModule + '\'' +
                ", opType='" + opType + '\'' +
                ", opParam='" + opParam + '\'' +
                ", opContent='" + opContent + '\'' +
                ", opHostName='" + opHostName + '\'' +
                ", opRes='" + opRes + '\'' +
                ", opRef='" + opRef + '\'' +
                ", opIp=" + opIp +
                ", serviceIp=" + serviceIp +
                ", source='" + source + '\'' +
                ", memo='" + memo + '\'' +
                ", cost=" + cost +
                ", opStartTm=" + opStartTm +
                ", opEndTm=" + opEndTm +
                ", threadId=" + threadId +
                '}';
    }
}
