package com.atjl.retry.domain;

import com.atjl.retry.domain.gen.TsProcessLog;

public class ProcessLogBiz extends TsProcessLog {
    //查询 数量 耗时
    private Long getCountCost;

    //当前页码
    private int curStartPage;

    private int curEndPage;

    private Object cond;

    private boolean preServiceResult;

    public boolean isPreServiceResult() {
        return preServiceResult;
    }

    public void setPreServiceResult(boolean preServiceResult) {
        this.preServiceResult = preServiceResult;
    }

//    private List<ExecuteStatusResp> processLog;


//    public void addLog(ExecuteStatusResp log) {
//        if (CollectionUtil.isEmpty(processLog)) {
//            processLog = new ArrayList<>();
//        }
//        if()
//        processLog.add(log);
//    }


    public Object getCond() {
        return cond;
    }

    public void setCond(Object cond) {
        this.cond = cond;
    }

    public int getCurEndPage() {
        return curEndPage;
    }

    public void setCurEndPage(int curEndPage) {
        this.curEndPage = curEndPage;
    }

    public int getCurStartPage() {
        return curStartPage;
    }

    public void setCurStartPage(int curStartPage) {
        this.curStartPage = curStartPage;
    }

    public Long getGetCountCost() {
        return getCountCost;
    }

    public void setGetCountCost(Long getCountCost) {
        this.getCountCost = getCountCost;
    }

//    public List<ExecuteStatusResp> getProcessLog() {
//        return processLog;
//    }

//    public void setProcessLog(List<ExecuteStatusResp> processLog) {
//        this.processLog = processLog;
//    }

}
