package com.atjl.dbservice.manager;

import com.atjl.dbservice.api.TgtDataNeedUpdateChecker;
import com.atjl.dbservice.api.domain.DbTableTransferConfig;
import com.atjl.dbservice.domain.KeyValue;
import com.atjl.dbservice.domain.SeparatedDatas;
import com.atjl.dbservice.mapper.biz.DataTransferMapper;
import com.atjl.dbservice.util.DataFilterUtil;
import com.atjl.util.collection.CollectionUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class TgtTableDataManager {
    @Resource
    private DataTransferMapper dataTransferMapper;

    public List<Map> getTgtData(List<Map> rawDatas, DbTableTransferConfig config) {
        List<List<KeyValue>> res = new ArrayList<>();
        for (Map rawData : rawDatas) {
            List<KeyValue> l = new ArrayList<>();
            Map<String, String> pkMappings = config.getPkFieldMapping();
            for (Map.Entry<String, String> pkM : pkMappings.entrySet()) {
                KeyValue cv = new KeyValue();
                cv.setKey(pkM.getValue());
                cv.setValue(String.valueOf(rawData.get(pkM.getKey())));
                l.add(cv);
            }
            res.add(l);
        }
        return dataTransferMapper.getTgtTableData(config, res);
    }


    /**
     * 分离 已经存在的 和 不存在的
     * 同时 按配置的过滤列，过滤
     */
    public SeparatedDatas separate(List<Map> rawDatas, DbTableTransferConfig config) {
        List<Map> tgtExistDatas = getTgtData(rawDatas, config);//已经存在的数据

        SeparatedDatas res = new SeparatedDatas();
        if (CollectionUtil.isEmpty(tgtExistDatas)) {
            res.setNotExistDatas(rawDatas);
            return res;
        }
//        config.getPkFieldMapping();
//        Map.Entry<String, String> entry = config.getPkFieldRandomOne();
//        String rawPk = entry.getKey();
//        String tgtPk = entry.getValue();

        Iterator<Map> it = rawDatas.iterator();
        List<Map> exist = new ArrayList<>();
        int existNoNeedUpdate = 0;
        int existNeedUpdate = 0;
        while (it.hasNext()) {
            Map rawData = it.next();
            for (Map tgtData : tgtExistDatas) {
                if (DataFilterUtil.isAllEqual(config.getPkFieldMapping(), rawData, tgtData)) {
                    TgtDataNeedUpdateChecker checker = config.getTgtDataUpdateCheck();
                    if (checker != null) {
                        if (!checker.needUpdate(rawData, tgtData)) {
                            existNoNeedUpdate++;
                            it.remove();
                            break;
                        }
                    }

                    rawData.put(config.getTgtTablePk(), tgtData.get(config.getTgtTablePk()));
                    exist.add(rawData);
                    it.remove();
                    break;
                }
            }
        }
        res.setNotExistDatas(rawDatas);
        res.setExistDatas(exist);
        res.setExistNeedUpdateCount(existNeedUpdate);
        res.setExistNoNeedUpdateCount(existNoNeedUpdate);
        res.setNeedAddCount(rawDatas.size());
        return res;
    }


    public void insert() {


    }
}
