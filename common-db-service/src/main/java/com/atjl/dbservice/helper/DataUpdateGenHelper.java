package com.atjl.dbservice.helper;

import com.atjl.dbservice.api.domain.DataBaseConfig;
import com.atjl.dbservice.api.domain.DataCoverteConfig;
import com.atjl.dbservice.api.domain.DataCpConfig;
import com.atjl.dbservice.api.domain.PropertyCovertor;
import com.atjl.common.domain.KeyValue;
import com.atjl.dbservice.domain.TgtTableDataUpdatePkg;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataUpdateGenHelper {
    private static final Logger logger = LoggerFactory.getLogger(DataUpdateGenHelper.class);

    public void genPkValues(List<Map> datas, DataBaseConfig config, TgtTableDataUpdatePkg res) {
        List<String> pks = new ArrayList<>();
        for (Map data : datas) {
            String pk = StringUtil.getEmptyString(data.get(config.getTgtTablePk()));
            if (!StringCheckUtil.isEmpty(pk)) {
                pks.add(pk);
            }
        }
        res.setPkValues(pks);
    }

    public void genUpdateInner(List<Map> datas, DataCpConfig config, Map<String, String> mapping, Map<String, List<KeyValue>> prop2listMap) {
        for (Map.Entry<String, String> pkentry : mapping.entrySet()) {
            String raw = pkentry.getKey();
            String tgt = pkentry.getValue();

            List<KeyValue> pk2propvalueList = prop2listMap.get(tgt);
            if (CollectionUtil.isEmpty(pk2propvalueList)) {
                pk2propvalueList = new ArrayList<>();
            }
            for (Map data : datas) {
                Object tgtPk = data.get(config.getTgtTablePk());
                if (StringCheckUtil.isEmpty(tgtPk)) {
                    continue;
                }
                String pk = String.valueOf(tgtPk);
                String v = StringUtil.getEmptyString(data.get(raw));
                KeyValue kv = new KeyValue();
                kv.setKey(pk);
                kv.setValue(v);
                pk2propvalueList.add(kv);
            }
            prop2listMap.put(tgt, pk2propvalueList);
        }
    }


    public int genUpdateInner(List<Map> datas, DataCoverteConfig config, List<PropertyCovertor> pcList, Map<String, List<KeyValue>> prop2listMap) {
        /**
         * 错误表
         * k: 主键值
         * v: 是否成功，出现一次转换失败，即设置为false
         */
        Map<String, Boolean> succMap = new HashMap<>();

        for (PropertyCovertor pc : pcList) {
            String tgt = pc.getTgtCol();
            List<KeyValue> pk2propvalueList = prop2listMap.get(tgt);
            if (CollectionUtil.isEmpty(pk2propvalueList)) {
                pk2propvalueList = new ArrayList<>();
            }
            for (Map data : datas) {
                Object pkValRaw = data.get(config.getTgtTablePk());
                if (StringCheckUtil.isEmpty(pkValRaw)) {
                    continue;
                }

                String pkVal = StringUtil.getEmptyString(pkValRaw);
                if (pkVal == null) {
                    continue;
                }

                KeyValue kv = new KeyValue();
                kv.setKey(pkVal);
                try {
                    String covResp = pc.getCovertor().coverte(data);
                    kv.setValue(covResp);
                } catch (Exception e) {
                    kv.setValue(pc.getDefaultValue());
                    succMap.put(pkVal, false);
                    logger.debug("covert value error {}", e);
                }
                pk2propvalueList.add(kv);
            }
            prop2listMap.put(tgt, pk2propvalueList);
        }
        int failCnt = 0;
        for (Map.Entry<String, Boolean> entry : succMap.entrySet()) {
            if (entry.getValue() != null && !entry.getValue()) {
                failCnt++;
            }
        }
        return failCnt;
    }


}
