package com.atjl.dbservice.manager;

import com.atjl.dbservice.domain.CondValue;
import com.atjl.dbservice.domain.DbTableTransferConfig;
import com.atjl.dbservice.mapper.biz.DataTransferMapper;
import com.atjl.util.character.StringCheckUtil;
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
        List<List<CondValue>> res = new ArrayList<>();
        for (Map rawData : rawDatas) {
            List<CondValue> l = new ArrayList<>();
            Map<String, String> pkMappings = config.getPkFieldMapping();
            for (Map.Entry<String, String> pkM : pkMappings.entrySet()) {
                CondValue cv = new CondValue();
                cv.setField(pkM.getValue());
                cv.setValue(String.valueOf(rawData.get(pkM.getKey())));
                l.add(cv);
            }
            res.add(l);
        }
        return dataTransferMapper.getTgtTableData(config, res);
    }

    /**
     * 分离 已经存在的 和 不存在的
     */
    public List<Map> filterExist(List<Map> rawDatas, DbTableTransferConfig config) {
        List<Map> existDatas = getTgtData(rawDatas, config);

        if (CollectionUtil.isEmpty(existDatas)) {
            return rawDatas;
        }

        Map.Entry<String, String> entry = config.getPkFieldRandomOne();
        String rawPk = entry.getKey();
        String tgtPk = entry.getValue();

        Iterator<Map> it = rawDatas.iterator();
        while (it.hasNext()) {
            Map data = it.next();
            for (Map existData : existDatas) {
                if (StringCheckUtil.equal(String.valueOf(data.get(rawPk)), String.valueOf(existData.get(tgtPk)))) {
                    it.remove();
                    break;
                }
            }
        }
        return rawDatas;
    }

    public void jsonGenerate() {

    }

    public void insert() {


    }
}
