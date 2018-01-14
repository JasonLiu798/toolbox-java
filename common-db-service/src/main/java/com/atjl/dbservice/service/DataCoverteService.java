package com.atjl.dbservice.service;

import com.atjl.dbservice.api.domain.DataCoverteConfig;
import com.atjl.dbservice.api.domain.PropertyCovertor;
import com.atjl.dbservice.api.domain.SearchCondBase;
import com.atjl.dbservice.domain.TgtTableDataUpdatePkg;
import com.atjl.dbservice.manager.DataUpdateManager;
import com.atjl.dbservice.manager.RawTableDataGetManager;
import com.atjl.retry.api.CustomGetSimpleDatas;
import com.atjl.retry.api.ExecuteBatchService;
import com.atjl.retry.api.GetOptionService;
import com.atjl.retry.api.domain.ExecuteStatusResp;
import com.atjl.util.collection.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public abstract class DataCoverteService implements CustomGetSimpleDatas<Map, SearchCondBase>, ExecuteBatchService<Map>, GetOptionService {
    @Resource
    private DataUpdateManager dataUpdateManager;
    @Resource
    private RawTableDataGetManager rawTableDataGetManager;

    public abstract DataCoverteConfig getConfig();

    @Override
    public int getRetryDataCount(SearchCondBase cond) {
        if (cond == null) {
            cond = new SearchCondBase();
        }
        DataCoverteConfig config = getConfig();
        return rawTableDataGetManager.getCount(config, cond);
    }

    @Override
    public List<Map> getRetryDataContextListPaged(int page, int pageSize, SearchCondBase cond) {
        if (cond == null) {
            cond = new SearchCondBase();
        }
        DataCoverteConfig config = getConfig();
        config.setCurrentPage(page);
        config.setPageSize(pageSize);
        return rawTableDataGetManager.getData(config, cond);
    }

    @Override
    public ExecuteStatusResp execute(List<Map> datas) {
        int rawSize = datas.size();
        int chkFailSize = 0;
        int upds = 0;

        List<PropertyCovertor> cv = getConfig().getCovertors();

        if (!CollectionUtil.isEmpty(datas) && !CollectionUtil.isEmpty(cv)) {
            TgtTableDataUpdatePkg updatePkg = dataUpdateManager.covGenUpdate(datas, getConfig());
            upds = dataUpdateManager.update(updatePkg, getConfig());
            if (upds > 0) {
                upds = rawSize - updatePkg.getFailCount();//updatePkg.getPkValues().size();
            } else {
                upds = 0;
            }
            chkFailSize = updatePkg.getFailCount();
        }

        return new ExecuteStatusResp(0, upds, 0, chkFailSize);
    }

}
