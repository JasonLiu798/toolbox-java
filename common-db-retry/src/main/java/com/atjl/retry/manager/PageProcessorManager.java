package com.atjl.retry.manager;

import com.atjl.common.constant.CommonConstant;
import com.atjl.retry.api.CustomGetSimpleDatas;
import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.ExecuteBatchService;
import com.atjl.retry.api.domain.ExecuteStatusResp;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.domain.ProcessLogBiz;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.util.OptionUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.PageUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 重试调度辅助
 * 分页调度
 */
@Component
public class PageProcessorManager {
    private static final Logger logger = LoggerFactory.getLogger(PageProcessorManager.class);

    @Resource
    GetDataManager retryGetManager;
    @Resource
    private ProcessManager retryProcessManager;
    @Resource
    private ProcessStatusManager processStatusManager;

    public void pageProcess(RetryServiceItem retryServiceItem, Object cond) {

        long totalStart = System.currentTimeMillis();
        long failPageCount = 0;

        PageOption opt = retryServiceItem.getPageOption();
        processStatusManager.repeatExecuteCheck(opt);

        Long logPk = processStatusManager.addStatus(opt.getServiceName());
        ProcessLogBiz log = new ProcessLogBiz();
        log.setDataProcessId(logPk);


        //获取总数
        long getCountStart = System.currentTimeMillis();
        int totalCount = retryGetManager.getTotalCount(retryServiceItem, cond);
        long getCountCost = System.currentTimeMillis() - getCountStart;
        log.setTotalCount(new Long(totalCount));
        log.setGetCountCost(getCountCost);

        if (totalCount == 0) {
            logger.info("process service {},get data count 0", opt.getServiceName());
            log.setProcessEnd(CommonConstant.YES);
            processStatusManager.updateByPk(log);
            return;
        }

        if (cond != null) {
            log.setCond(cond);
        }

        logger.info("process service {},get data count {}", opt.getServiceName(), totalCount);

        int pageSize = OptionUtil.getPageCount(cond, opt);
        int pageCnt = PageUtil.getPageCount(totalCount, pageSize);
        logger.info("process service {},get page count {}", opt.getServiceName(), pageCnt);
        String startId = null;

        log.setTotalPage(new Long(pageCnt));

        processStatusManager.updateByPk(log);

        long sumFail = 0;
        long sumAdd = 0;
        long sumUpd = 0;
        long sumUnFail = 0;
        long sumNoNeedUpd = 0;
        for (int i = 1; i <= pageCnt; i++) {
            //按页获取数据
            logger.debug("process page {},pagesize {},pagecnt {},total {}", i, pageSize, pageCnt, totalCount);
            log.setCurStartPage(i);
            processStatusManager.updateByPk(log);

            List datas = null;
            if (retryServiceItem.getRetryOption() != null) {
                datas = retryGetManager.getRetryDatas(retryServiceItem.getRetryOption(), startId);
            } else {
                if (opt.isBatchProcess()) {
                    CustomGetSimpleDatas service = retryServiceItem.getCustomGetSimpleDatas();
                    datas = service.getRetryDataContextListPaged(i, pageSize, cond);
                } else {
                    datas = retryGetManager.getDatas(opt, retryServiceItem.getCustomGetDatas(), retryServiceItem.getCustomGetDatasUseIdPage(), i, startId);
                }
            }

            if (CollectionUtil.isEmpty(datas)) {
                logger.warn("get process data empty,service {},page {},pageCnt {}", retryServiceItem.getPageOption().getServiceName(), i, pageCnt);
                continue;
            }

            if (opt.isBatchProcess()) {
                //批量处理
                ExecuteBatchService executeBatchService = retryServiceItem.getExecuteBatchService();
                ExecuteStatusResp executeResp = new ExecuteStatusResp();
                long pageStart = System.currentTimeMillis();
                try {
                    executeResp = executeBatchService.execute(datas);
                    /**
                     *         int sumTotal = 0;
                     int sumFail = 0;
                     int sumAdd = 0;
                     int sumUpd = 0;
                     */
                    sumAdd += executeResp.getSuccAddCount();
                    sumUpd += executeResp.getSuccUpdateCount();
                    sumFail += executeResp.getKnownFailCount();
                    sumNoNeedUpd += executeResp.getKnownNoneedUpdCount();
                    logger.info("process batch page {},succ {}", i, JSONFastJsonUtil.objectToJson(executeResp));
                } catch (Exception e) {
                    logger.error("process batch error ", e);
                    sumUnFail += pageCnt;
                    failPageCount++;
                }
                long pageCost = System.currentTimeMillis() - pageStart;
                executeResp.setCost(pageCost);
                executeResp.setPage(i);

                log.setAddCount(sumAdd);
                log.setUpdCount(sumUpd);
                log.setFailCount(sumFail);
                log.setUnknownFailCount(sumUnFail);
                log.setNoNeedUpdCount(sumNoNeedUpd);
                executeResp.setProcessLogId(log.getDataProcessId());
                processStatusManager.addLogDetail(executeResp);
                //log.addLog(executeResp);
                log.setCurEndPage(i);
                processStatusManager.updateByPk(log);
            } else {
                //单条处理
                int lastIdx = datas.size() - 1;
                for (int j = 0; j < datas.size(); j++) {
                    DataContext data = (DataContext) datas.get(j);
                    logger.info("process item {}", JSONFastJsonUtil.objectToJson(data));
                    retryProcessManager.processItem(retryServiceItem, data);
                    if (j == lastIdx) {
                        startId = data.getId();
                    }
                }
            }
        }
        long totalCost = System.currentTimeMillis() - totalStart;
        log.setTotalCost(totalCost);
        log.setProcessEnd(CommonConstant.YES);
        log.setFailPageCount(failPageCount);
        processStatusManager.updateByPk(log);
    }


}
