package com.atjl.retry.manager;

import com.atjl.retry.api.*;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.service.AfterDefaultService;
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
    AfterDefaultService retryAfterServiceBase;
    @Resource
    GetDataManager retryGetManager;
    @Resource
    private ProcessManager retryProcessManager;

    public void pageProcess(RetryServiceItem retryServiceItem, Object cond) {
        PageOption opt = retryServiceItem.getPageOption();

        //获取总数
        int totalCount = retryGetManager.getTotalCount(retryServiceItem, cond);
        if (totalCount == 0) {
            logger.info("process service {},get retry data count 0", opt.getServiceName());
            return;
        }

        int pageCnt = PageUtil.getPageCount(totalCount, opt.getPageSize());
        String startId = null;

        for (int i = 1; i <= pageCnt; i++) {
            //按页获取数据
            logger.debug("process page {},pagesize {},pagecnt {},total {}", i, opt.getPageSize(), pageCnt, totalCount);

            List datas = null;
            if (retryServiceItem.getRetryOption() != null) {
                datas = retryGetManager.getRetryDatas(retryServiceItem.getRetryOption(), startId);
            } else {
                if (opt.isBatchProcess()) {
                    CustomGetSimpleDatas service = retryServiceItem.getCustomGetSimpleDatas();
                    datas = service.getRetryDataContextListPaged(i, cond);
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
                try {
                    executeBatchService.execute(datas);
                } catch (Exception e) {
                    logger.error("process batch error ", e);
                }
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
    }


}
