package com.atjl.retry.manager;

import com.atjl.retry.api.CustomGetDatas;
import com.atjl.retry.api.CustomGetDatasUseIdPage;
import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.option.InitOption;
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

    
    public void pageProcess(RetryServiceItem retryServiceItem) {
        InitOption opt = retryServiceItem.getInitOption();
        CustomGetDatas getDatas = retryServiceItem.getRetryServiceCustomGetDatas();
        CustomGetDatasUseIdPage getDatasUseIdPage = retryServiceItem.getRetryServiceGetDatasUseIdPage();

        int totalCount = retryGetManager.getTotalCount(opt, getDatas, getDatasUseIdPage);
        if (totalCount == 0) {
            logger.info("process service {},get retry data count 0", opt.getServiceName());
            return;
        }

        int pageCnt = PageUtil.getPageCount(totalCount, opt.getPageSize());
        String startId = null;

        for (int i = 1; i <= pageCnt; i++) {
            //按页获取数据
            logger.debug("process page {},pagesize {},pagecnt {},total {}", i, opt.getPageSize(), pageCnt, totalCount);
            List<DataContext> datas = retryGetManager.getDatas(opt, getDatas, getDatasUseIdPage, i, startId);

            if (CollectionUtil.isEmpty(datas)) {
                logger.warn("get process data empty,service {},page {},pageCnt {}", retryServiceItem.getInitOption().getServiceName(), i, pageCnt);
                continue;
            }

            //处理当页数据
            int lastIdx = datas.size() - 1;
            for (int j = 0; j < datas.size(); j++) {
                DataContext data = datas.get(j);
                logger.info("process item {}", JSONFastJsonUtil.objectToJson(data));
                retryProcessManager.processItem(retryServiceItem, data);
                if (j == lastIdx) {
                    startId = data.getId();
                }
            }
        }
    }


}
