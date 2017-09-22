package com.atjl.retry.manager;

import com.atjl.common.api.req.PageReqV1;
import com.atjl.retry.api.CustomGetDatas;
import com.atjl.retry.api.CustomGetDatasUseIdPage;
import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.DataContextFactory;
import com.atjl.retry.api.option.InitOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import com.atjl.retry.mapper.biz.RetryMapper;
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


    public int getTotalCount(InitOption opt, CustomGetDatas getDatas, CustomGetDatasUseIdPage getDatasUseIdPage) {
        int totalCount;
        if (OptionUtil.isCustomGetDatas(opt)) {
            totalCount = getDatas.getRetryDataCount();
        } else if (OptionUtil.isCustomGetDatasUseId(opt)) {
            totalCount = getDatasUseIdPage.getRetryDataCount();
        } else {
            totalCount = getCount(opt);
        }
        return totalCount;
    }


    public List<DataContext> getDatas(InitOption opt, CustomGetDatas getDatas, CustomGetDatasUseIdPage getDatasUseIdPage, int i, String startId) {
        List<DataContext> datas;
        if (OptionUtil.isCustomGetDatas(opt)) {
            datas = getDatas.getRetryDataContextListPaged(i, opt.getPageSize());
        } else if (OptionUtil.isCustomGetDatasUseId(opt)) {
            datas = getDatasUseIdPage.getRetryDataContextListPaged(startId, opt.getPageSize());
        } else {
            datas = getDataContextDefault(opt, startId, opt.getPageSize());
        }
        return datas;
    }


    /**
     * 默认获取需要重试的数量
     *
     * @param initOption 选项
     * @return 总数
     */
    public int getCount(InitOption initOption) {
        return retryMapper.getDataCount(initOption, initOption.getRetryTabMeta());
    }


    /**
     * 获取数据
     *
     * @param initOption 选项
     * @param pageSize   页大小
     * @return 结果集
     */
    public List<DataContext> getDataContextDefault(InitOption initOption, String startId, int pageSize) {
        PageReqV1 pageReq = new PageReqV1(startId, new Long((long) pageSize));
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
