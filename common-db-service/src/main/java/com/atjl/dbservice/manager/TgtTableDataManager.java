package com.atjl.dbservice.manager;

import com.atjl.dbservice.domain.*;
import com.atjl.dbservice.mapper.biz.DataTransferMapper;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

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
    public SeparatedDatas separate(List<Map> rawDatas, DbTableTransferConfig config) {
        List<Map> existDatas = getTgtData(rawDatas, config);//已经存在的数据
        SeparatedDatas res = new SeparatedDatas();
        if (CollectionUtil.isEmpty(existDatas)) {
        	res.setNotExistDatas(rawDatas);
        	return res;
        }
        Map.Entry<String, String> entry = config.getPkFieldRandomOne();
        String rawPk = entry.getKey();
        String tgtPk = entry.getValue();

        Iterator<Map> it = rawDatas.iterator();
        List<Map> exist = new ArrayList<>();
        while (it.hasNext()) {
            Map data = it.next();
            for (Map existData : existDatas) {
                if (StringCheckUtil.equal(String.valueOf(data.get(rawPk)), String.valueOf(existData.get(tgtPk)))) {
					data.put( config.getTgtTablePk(), existData.get(config.getTgtTablePk()) );
                	exist.add(data);
                    it.remove();
                    break;
                }
            }
        }
        res.setNotExistDatas(rawDatas);
        res.setExistDatas(exist);
        return res;
    }
	


    public void insert() {


    }
}
