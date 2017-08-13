package com.atjl.log.api;

import java.io.Serializable;
import java.util.Date;

public class LogDto implements Serializable {
    private Long logId;

    private Date opTm;

    private String logLv;

    private String opModule;

    private String opChildModuel;

    private String empNum;

    private String opType;

    private String opParam;

    private String opRes;

    private String opRef;

    private Long cost;

    private Long serviceIp;

    private Long opIp;

    private String userName;

    private String source;

    private String opHostName;

    private String userExtInfo;

    private String ext1;

    private String ext2;

    private String ext3;

    private String opContent;

    private static final long serialVersionUID = 1L;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Date getOpTm() {
        return opTm;
    }

    public void setOpTm(Date opTm) {
        this.opTm = opTm;
    }

    public String getLogLv() {
        return logLv;
    }

    public void setLogLv(String logLv) {
        this.logLv = logLv == null ? null : logLv.trim();
    }

    public String getOpModule() {
        return opModule;
    }

    public void setOpModule(String opModule) {
        this.opModule = opModule == null ? null : opModule.trim();
    }

    public String getOpChildModuel() {
        return opChildModuel;
    }

    public void setOpChildModuel(String opChildModuel) {
        this.opChildModuel = opChildModuel == null ? null : opChildModuel.trim();
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum == null ? null : empNum.trim();
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public String getOpParam() {
        return opParam;
    }

    public void setOpParam(String opParam) {
        this.opParam = opParam == null ? null : opParam.trim();
    }

    public String getOpRes() {
        return opRes;
    }

    public void setOpRes(String opRes) {
        this.opRes = opRes == null ? null : opRes.trim();
    }

    public String getOpRef() {
        return opRef;
    }

    public void setOpRef(String opRef) {
        this.opRef = opRef == null ? null : opRef.trim();
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(Long serviceIp) {
        this.serviceIp = serviceIp;
    }

    public Long getOpIp() {
        return opIp;
    }

    public void setOpIp(Long opIp) {
        this.opIp = opIp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getOpHostName() {
        return opHostName;
    }

    public void setOpHostName(String opHostName) {
        this.opHostName = opHostName == null ? null : opHostName.trim();
    }

    public String getUserExtInfo() {
        return userExtInfo;
    }

    public void setUserExtInfo(String userExtInfo) {
        this.userExtInfo = userExtInfo == null ? null : userExtInfo.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent == null ? null : opContent.trim();
    }
}