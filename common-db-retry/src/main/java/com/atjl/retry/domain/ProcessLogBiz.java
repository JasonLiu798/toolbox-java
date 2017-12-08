package com.atjl.retry.domain;

import com.atjl.retry.domain.gen.TsProcessLog;

public class ProcessLogBiz extends TsProcessLog {
    //查询 数量 耗时
    private Long getCountCost;

//    private List<ExecuteStatusResp> processLog;


//    public void addLog(ExecuteStatusResp log) {
//        if (CollectionUtil.isEmpty(processLog)) {
//            processLog = new ArrayList<>();
//        }
//        if()
//        processLog.add(log);
//    }

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
