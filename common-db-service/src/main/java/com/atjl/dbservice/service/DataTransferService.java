package com.atjl.dbservice.service;

import com.atjl.dbservice.api.RawDataValidator;
import com.atjl.dbservice.api.domain.DataCpConfig;
import com.atjl.dbservice.api.domain.SearchCondBase;
import com.atjl.dbservice.domain.*;
import com.atjl.dbservice.manager.DataImportManager;
import com.atjl.dbservice.manager.DataUpdateManager;
import com.atjl.dbservice.manager.RawTableDataGetManager;
import com.atjl.dbservice.manager.TgtTableDataManager;
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
public abstract class DataTransferService implements CustomGetSimpleDatas<Map, SearchCondBase>, ExecuteBatchService<Map>,GetOptionService {

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
        int rawSize = datas.size();
        int chkFailSize = 0;
        int updChkFailSize = 0;
        if (!CollectionUtil.isEmpty(datas)) {
            RawDataValidator v = getTransConfig().getRawDataValidator();
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

        //分离 已经存在 和 不存在 数据
        SeparatedDatas sepDatas = tgtTableDataManager.separate(datas, getTransConfig());
        updChkFailSize += sepDatas.getExistNoNeedUpdateCount();

        //生成 插入数据
        TgtTableDataPkg insertPkg = dataImportManager.tgtDataGenInsert(sepDatas.getNotExistDatas(), getTransConfig());
        int ins = dataImportManager.insert(insertPkg, getTransConfig());
        //生成 修改数据
        TgtTableDataUpdatePkg updatePkg = dataUpdateManager.tgtDataGenUpdate(sepDatas.getExistDatas(), getTransConfig());

        int upds = dataUpdateManager.update(updatePkg, getTransConfig());
        if (upds > 0) {
            upds = rawSize - ins - chkFailSize - updChkFailSize;
        }
        return new ExecuteStatusResp(ins, upds, chkFailSize, updChkFailSize);
    }

}
