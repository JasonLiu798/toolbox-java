package com.atjl.retry.manager;

import com.atjl.common.api.req.PageIntReqV1;
import com.atjl.retry.api.*;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.mapper.RetryMapper;
import com.atjl.retry.util.OptionUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionFilterUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.CovertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GetDataManager {
    private static final Logger logger = LoggerFactory.getLogger(GetDataManager.class);
    @Resource
    private RetryMapper retryMapper;


    public int getTotalCount(RetryServiceItem retryServiceItem, Object cond) {
        CustomGetCount customGetCount = retryServiceItem.getCustomGetCount();
        //int totalCount = 0;
        if (retryServiceItem.getRetryOption() != null) {
            return getRetryTotalCount(retryServiceItem.getRetryOption());
        } else {
            return getTotalCount(retryServiceItem.getPageOption(), customGetCount, cond);
        }
    }


    public int getTotalCount(PageOption opt, CustomGetCount getCount, Object cond) {
        int totalCount = 0;
        if (OptionUtil.isCustomGetDatas(opt)) {
            totalCount = getCount.getRetryDataCount(cond);
        } else if (OptionUtil.isCustomGetDatasUseId(opt)) {
            totalCount = getCount.getRetryDataCount(cond);
        }
        return totalCount;
    }

    /**
     * 默认获取需要重试的数量
     *
     * @param retryOption 选项
     * @return 总数
     */
    public int getRetryTotalCount(RetryOption retryOption) {
        return retryMapper.getDataCount(retryOption, retryOption.getRetryTabMeta());
    }

    /**
     * 自定义取数
     */
    public List<DataContext> getDatas(PageOption opt, CustomGetDatas getDatas, CustomGetDatasUseIdPage getDatasUseIdPage, int i, String startId) {
        List<DataContext> datas = new ArrayList<>();
        if (OptionUtil.isCustomGetDatas(opt)) {
            datas = getDatas.getRetryDataContextListPaged(i);
        } else if (OptionUtil.isCustomGetDatasUseId(opt)) {
            datas = getDatasUseIdPage.getRetryDataContextListPaged(startId, opt.getPageSize());
//        } else {
//            RetryOption ropt = (RetryOption) opt;
//            datas = getRetryDatas(ropt, startId, opt.getPageSize());
        }
        return datas;
    }


    /**
     * 获取数据
     *
     * @param initOption 选项
     * @return 结果集
     */
    public List<DataContext> getRetryDatas(RetryOption initOption, String startId) {
        PageIntReqV1 pageReq = new PageIntReqV1(startId, initOption.getPageSize());
        List<Map<String, Object>> datas = retryMapper.selectDatas(initOption, initOption.getRetryTabMeta(), pageReq);

        List<DataContext> contexts = new ArrayList<>();
        if (CollectionUtil.isEmpty(datas)) {
            return contexts;
        }

        RetryTableMetaConf meta = initOption.getRetryTabMeta();
        for (Map<String, Object> data : datas) {
            List<String> innerCols = new ArrayList<>();
            innerCols.add(meta.getIdCol());
            innerCols.add(meta.getRetryCountCol());
            innerCols.add(meta.getLastRetriedTsCol());

            Map<String, Object> userDatas = CollectionFilterUtil.filterMap(data, innerCols);
            DataContext item = DataContextFactory.build(userDatas);
            String id = CovertUtil.covertObj(data.get(meta.getIdCol()), String.class);
            if (StringCheckUtil.isEmpty(id)) {
                logger.warn("get datas id null");
                continue;
            }
            item.setId(id);

            item.setRetriedCnt(CovertUtil.covertObj(data.get(meta.getRetryCountCol()), Long.class));

            item.setLastRetriedTs(CovertUtil.covertObj(data.get(meta.getLastRetriedTsCol()), Long.class));
            contexts.add(item);
        }
        return contexts;
    }


//
//    @Override
//    public List<DataContext> getRetryDataContextListPaged(int page, int pageSize) {
//        return null;
//    }
}
