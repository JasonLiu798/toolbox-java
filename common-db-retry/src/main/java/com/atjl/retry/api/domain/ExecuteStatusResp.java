package com.atjl.retry.api.domain;

import com.atjl.retry.domain.gen.TsProcessLogDetail;
import com.atjl.util.common.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "处理状态返回值")
public class ExecuteStatusResp extends TsProcessLogDetail {

    public ExecuteStatusResp() {
        this.processTm = DateUtil.getNowFmtDate();
    }

//    public boolean isNormal() {
//        if (cost > 10000L) {
//            return false;
//        }
//        return true;
//    }


    public ExecuteStatusResp(int succInsertCount, int succUpdateCount, int knownFailCount, int updChkFailCount) {
        this.succAddCount = succInsertCount;
        this.succUpdateCount = succUpdateCount;
        this.knownFailCount = knownFailCount;
        this.knownNoneedUpdCount = updChkFailCount;
        this.processTm = DateUtil.getNowFmtDate();
    }

    @ApiModelProperty(value = "整体成功/失败")
    private boolean succ = true;

    @ApiModelProperty(value = "页号")
    private int page = 0;

    @ApiModelProperty(value = "成功插入数量")
    private int succAddCount = 0;
    @ApiModelProperty(value = "成功更新数量")
    private int succUpdateCount = 0;

    @ApiModelProperty(value = "已知初步校验异常的数量")
    private int knownFailCount = 0;

    @ApiModelProperty(value = "已知更新校验，不需要更新的数量")
    private int knownNoneedUpdCount = 0;

    @ApiModelProperty(value = "处理时间戳")
    private String processTm;

    @ApiModelProperty(value = "当前页处理时间，系统填充，无需填写")
    private Long cost;

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getProcessTm() {
        return processTm;
    }

    public void setProcessTm(String processTm) {
        this.processTm = processTm;
    }

    public int getSuccAddCount() {
        return succAddCount;
    }

    public void setSuccAddCount(int succAddCount) {
        this.succAddCount = succAddCount;
    }

    public int getSuccUpdateCount() {
        return succUpdateCount;
    }

    public void setSuccUpdateCount(int succUpdateCount) {
        this.succUpdateCount = succUpdateCount;
    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }


    public int getKnownFailCount() {
        return knownFailCount;
    }

    public void setKnownFailCount(int knownFailCount) {
        this.knownFailCount = knownFailCount;
    }

    public int getKnownNoneedUpdCount() {
        return knownNoneedUpdCount;
    }

    public void setKnownNoneedUpdCount(int knownNoneedUpdCount) {
        this.knownNoneedUpdCount = knownNoneedUpdCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
