package com.atjl.retry.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "处理状态返回值")
public class ExecuteStatusResp {

    public ExecuteStatusResp() {
    }

    @ApiModelProperty(value = "整体成功/失败")
    private boolean succ = true;

    @ApiModelProperty(value = "成功数量")
    private int succCount = 0;
    @ApiModelProperty(value = "已知异常数量")
    private int knownFailCount = 0;
    @ApiModelProperty(value = "未知异常数量")
    private int unknownFailCount = 0;

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public int getSuccCount() {
        return succCount;
    }

    public void setSuccCount(int succCount) {
        this.succCount = succCount;
    }

    public int getKnownFailCount() {
        return knownFailCount;
    }

    public void setKnownFailCount(int knownFailCount) {
        this.knownFailCount = knownFailCount;
    }

    public int getUnknownFailCount() {
        return unknownFailCount;
    }

    public void setUnknownFailCount(int unknownFailCount) {
        this.unknownFailCount = unknownFailCount;
    }
}
