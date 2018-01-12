package com.atjl.dbservice.manager;

import com.atjl.dbservice.api.domain.DataBaseConfig;
import com.atjl.dbservice.api.domain.DataCoverteConfig;
import com.atjl.dbservice.api.domain.DataCpConfig;
import com.atjl.common.domain.KeyValue;
import com.atjl.dbservice.domain.TgtTableDataUpdatePkg;
import com.atjl.dbservice.helper.DataUpdateGenHelper;
import com.atjl.dbservice.mapper.biz.DataTransferMapper;
import com.atjl.dbservice.util.DataFieldUtil;
import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据插入辅助类
 */
@Component
public class DataUpdateManager {
    @Resource
    DataTransferMapper dataTransferMapper;
    @Resource
    private DataUpdateGenHelper dataUpdateGenHelper;

    /**
     * 实际的更新操作
     */
    public int update(TgtTableDataUpdatePkg dataPkg, DataBaseConfig config) {
        if (!CollectionUtil.isEmpty(dataPkg.getItems())) {
            return dataTransferMapper.updateBatch(config, dataPkg);
        }
        return 0;
    }

    /**
     * 生成 需要更新的数据结构
     */
    public TgtTableDataUpdatePkg tgtDataGenUpdate(List<Map> datas, DataCpConfig config) {
        /**
         Map<String, List<KeyValue>> items;
         List<KeyValue> pkValues;
         */
        TgtTableDataUpdatePkg res = new TgtTableDataUpdatePkg();
        //List<TgtTableData> res = new ArrayList<>();
        if (CollectionUtil.isEmpty(datas)) {
            return res;
        }
        Map<String, List<KeyValue>> prop2listMap = new HashMap<>();

        dataUpdateGenHelper.genPkValues(datas, config, res);


        //主键部分
        if (!CollectionUtil.isEmpty(config.getPkFieldMapping())) {
            dataUpdateGenHelper.genUpdateInner(datas, config, config.getPkFieldMapping(), prop2listMap);
        }
        //普通 部分
        if (!CollectionUtil.isEmpty(config.getFieldMapping())) {
            dataUpdateGenHelper.genUpdateInner(datas, config, config.getFieldMapping(), prop2listMap);
        }
        //比较 部分
//        if (!CollectionUtil.isEmpty(config.getNoUpdateCheckMapping())) {
//            genUpdateInner(datas, config, config.getNoUpdateCheckMapping(), prop2listMap);
//        }

        //json 部分
        if (!CollectionUtil.isEmpty(config.getJsonFieldMapping())) {
            for (Map data : datas) {
                Map<String, String> jsonMap = DataFieldUtil.raw2tgt(data, config.getJsonFieldMapping());
                String json = JSONFastJsonUtil.objectToJson(jsonMap);

                List<KeyValue> pk2propvalueList = prop2listMap.get(config.getJsonField());
                if (CollectionUtil.isEmpty(pk2propvalueList)) {
                    pk2propvalueList = new ArrayList<>();
                }

                KeyValue kv = new KeyValue();
                String pk = StringUtil.getEmptyString(data.get(config.getTgtTablePk()));
                kv.setKey(pk);
                kv.setValue(json);
                pk2propvalueList.add(kv);
                prop2listMap.put(config.getJsonField(), pk2propvalueList);
            }
        }

        //默认值
        if (!CollectionUtil.isEmpty(config.getDefaultValues())) {
            for (Map.Entry<String, String> dftEntry : config.getDefaultValues().entrySet()) {
                String propName = dftEntry.getKey();
                List<KeyValue> pk2propvalueList = new ArrayList<>();

                for (Map data : datas) {
                    KeyValue kv = new KeyValue();
                    String pk = StringUtil.getEmptyString(data.get(config.getTgtTablePk()));
                    kv.setKey(pk);
                    kv.setValue(dftEntry.getValue());
                    pk2propvalueList.add(kv);
                }
                prop2listMap.put(propName, pk2propvalueList);
            }
        }

        res.setItems(prop2listMap);
        return res;
    }


    /**
     * 生成 需要更新的数据结构
     */
    public TgtTableDataUpdatePkg covGenUpdate(List<Map> datas, DataCoverteConfig config) {
        /**
         Map<String, List<KeyValue>> items;
         List<KeyValue> pkValues;
         */
        TgtTableDataUpdatePkg res = new TgtTableDataUpdatePkg();
        //List<TgtTableData> res = new ArrayList<>();
        if (CollectionUtil.isEmpty(datas)) {
            return res;
        }
        Map<String, List<KeyValue>> prop2listMap = new HashMap<>();

        dataUpdateGenHelper.genPkValues(datas, config, res);//主键 列表

        //转换列表
        if (!CollectionUtil.isEmpty(config.getCovertors())) {
            int failCnt = dataUpdateGenHelper.genUpdateInner(datas, config, config.getCovertors(), prop2listMap);
            res.setFailCount(failCnt);
        }
        res.setItems(prop2listMap);
        return res;
    }


//    private String getStr(Object pkObj) {
//        String v = "";
//        if (pkObj != null) {
//            v = String.valueOf(pkObj);
//        }
//        return v;
//    }


//    private List<KeyValue> raw2tgtKV(Map rawKV, Map<String, String> mapping) {
//        List<KeyValue> res = new ArrayList<>();
//        for (Map.Entry<String, String> pk : mapping.entrySet()) {
//            String raw = pk.getKey();
//            String tgt = pk.getValue();
//            Object v = rawKV.get(raw);
//            KeyValue kv = new KeyValue();
//            kv.setKey(tgt);
//            if (v != null) {
//                kv.setValue(String.valueOf(v));
//            }
//        }
//        return res;
//    }
}
