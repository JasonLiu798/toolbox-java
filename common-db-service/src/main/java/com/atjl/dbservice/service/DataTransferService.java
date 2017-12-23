package com.atjl.dbservice.service;

import com.atjl.dbservice.api.RawDataValidator;
import com.atjl.dbservice.api.domain.DataCpConfig;
import com.atjl.dbservice.api.domain.SearchCondBase;
import com.atjl.dbservice.domain.*;
import com.atjl.dbservice.manager.DataImportManager;
import com.atjl.dbservice.manager.DataUpdateManager;
import com.atjl.dbservice.manager.RawTableDataGetManager;
import com.atjl.dbservice.manager.TgtTableDataManager;
import com.atjl.dbservice.util.DataFilterUtil;
import com.atjl.retry.api.CustomGetSimpleDatas;
import com.atjl.retry.api.ExecuteBatchService;
import com.atjl.retry.api.GetOptionService;
import com.atjl.retry.api.domain.ExecuteStatusResp;
import com.atjl.util.collection.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public abstract class DataTransferService implements CustomGetSimpleDatas<Map, SearchCondBase>, ExecuteBatchService<Map>, GetOptionService {

    @Resource
    private DataImportManager dataImportManager;
    @Resource
    private DataUpdateManager dataUpdateManager;

    @Resource
    private RawTableDataGetManager rawTableDataGetManager;
    @Resource
    private TgtTableDataManager tgtTableDataManager;

    public abstract DataCpConfig getTransConfig();

    @Override
    public int getRetryDataCount(SearchCondBase cond) {
        if (cond == null) {
            cond = new SearchCondBase();
        }
        DataCpConfig config = getTransConfig();
        return rawTableDataGetManager.getCount(config, cond);
    }

    @Override
    public List<Map> getRetryDataContextListPaged(int page, int pageSize, SearchCondBase cond) {
        if (cond == null) {
            cond = new SearchCondBase();
        }
        DataCpConfig config = getTransConfig();
        config.setCurrentPage(page);
        config.setPageSize(pageSize);
        //config.getRawDataValidator();
        return rawTableDataGetManager.getData(config, cond);
    }


    @Override
    public ExecuteStatusResp execute(List<Map> datas) {
        DataCpConfig config = getTransConfig();
        int rawSize = datas.size();
        int chkFailSize = 0;
        int updChkFailSize = 0;
        if (!CollectionUtil.isEmpty(datas)) {
            RawDataValidator v = config.getRawDataValidator();
            if (v != null) {
                Iterator<Map> iterator = datas.iterator();
                while (iterator.hasNext()) {
                    Map data = iterator.next();
                    if (!v.valid(data)) {
                        iterator.remove();
                        chkFailSize++;
                    }
                }
            }
        }

        if (CollectionUtil.isEmpty(datas)) {
            return new ExecuteStatusResp(0, 0, rawSize, 0);
        }

        //过滤当页重复数据
        List<Map> filtered = DataFilterUtil.filterDuplicate(config, datas);

        if (CollectionUtil.isEmpty(filtered)) {
            return new ExecuteStatusResp(0, 0, rawSize, 0);
        } else {
            int bfSize = datas.size();
            int filteredSize = filtered.size();
            if (filteredSize < bfSize) {
                updChkFailSize += bfSize - filteredSize;
            }
        }

        //分离 已经存在 和 不存在 数据
        SeparatedDatas sepDatas = tgtTableDataManager.separate(filtered, config);
        updChkFailSize += sepDatas.getExistNoNeedUpdateCount();

        //生成 插入数据
        TgtTableDataPkg insertPkg = dataImportManager.tgtDataGenInsert(sepDatas.getNotExistDatas(), config);
        int ins = dataImportManager.insert(insertPkg, config);
        //生成 修改数据
        TgtTableDataUpdatePkg updatePkg = dataUpdateManager.tgtDataGenUpdate(sepDatas.getExistDatas(), config);

        int upds = dataUpdateManager.update(updatePkg, config);
        if (upds > 0) {
            upds = rawSize - ins - chkFailSize - updChkFailSize;
        }
        return new ExecuteStatusResp(ins, upds, chkFailSize, updChkFailSize);
    }

}
