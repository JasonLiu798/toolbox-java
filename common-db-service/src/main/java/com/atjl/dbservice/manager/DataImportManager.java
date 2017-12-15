package com.atjl.dbservice.manager;

import com.atjl.dbservice.api.domain.DataCpConfig;
import com.atjl.dbservice.domain.KeyValue;
import com.atjl.dbservice.domain.TgtTableData;
import com.atjl.dbservice.domain.TgtTableDataPkg;
import com.atjl.dbservice.mapper.biz.DataTransferMapper;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 数据插入辅助类
 */
@Component
public class DataImportManager {

    /**
     * 生成 需要插入的数据
     */
    public TgtTableDataPkg tgtDataGenInsert(List<Map> datas, DataCpConfig config) {
        TgtTableDataPkg res = new TgtTableDataPkg();
        //List<TgtTableData> res = new ArrayList<>();
        if (CollectionUtil.isEmpty(datas)) {
            return res;
        }
        List<TgtTableData> tgtDatas = new ArrayList<>();

        for (Map data : datas) {
            TgtTableData pkg = new TgtTableData();
            //List<KeyValue> l = new ArrayList<>();
            Map<String, String> tgtData = new TreeMap<>();//初始化
            //主键部分
            if (!CollectionUtil.isEmpty(config.getPkFieldMapping())) {
                tgtData.putAll(raw2tgt(data, config.getPkFieldMapping()));
            }
            //普通字段
            if (!CollectionUtil.isEmpty(config.getFieldMapping())) {
                tgtData.putAll(raw2tgt(data, config.getFieldMapping()));
            }
            // 比较部分
//            if (!CollectionUtil.isEmpty(config.getNoUpdateCheckMapping())) {
//                tgtData.putAll(raw2tgt(data, config.getNoUpdateCheckMapping()));
//            }

            //json 部分
            if (!CollectionUtil.isEmpty(config.getJsonFieldMapping())) {
                Map<String, String> jsonMap = raw2tgt(data, config.getJsonFieldMapping());
                String json = JSONFastJsonUtil.objectToJson(jsonMap);
                tgtData.put(config.getJsonField(), json);
            }

            //默认值
            if (!CollectionUtil.isEmpty(config.getDefaultValues())) {
                tgtData.putAll(config.getDefaultValues());
            }

//            //目标表主键字段，已经用于 更新已经存在的数据
//            Object tgtPk = data.get(config.getTgtTablePk());
//            if (!StringCheckUtil.isEmpty(tgtPk)) {
//                //tgtData.put( config.getTgtTablePk(), String.valueOf(tgtPk));
//                pkg.setPk(new KeyValue(config.getTgtTablePk(), String.valueOf(tgtPk)));
//            }

            List<KeyValue> kvs = config.getAllTgtSortKV();
            for (KeyValue kv : kvs) {
                kv.setValue(tgtData.get(kv.getKey()));
            }
            pkg.setItems(kvs);
            tgtDatas.add(pkg);
        }
        res.setDatas(tgtDatas);
        res.setFields(config.getAllTgtSortFields());
        return res;
    }


    private Map<String, String> raw2tgt(Map rawKV, Map<String, String> mapping) {
        Map<String, String> res = new HashMap<>();
        for (Map.Entry<String, String> pk : mapping.entrySet()) {
            String raw = pk.getKey();
            String tgt = pk.getValue();
            Object v = rawKV.get(raw);
            if (v == null) {
                res.put(tgt, "");
            } else {
                res.put(tgt, String.valueOf(v));
            }
        }
        return res;
    }

    @Resource
    DataTransferMapper dataTransferMapper;

    public int insert(TgtTableDataPkg dataPkg, DataCpConfig config) {
        if (!CollectionUtil.isEmpty(dataPkg.getDatas())) {
            return dataTransferMapper.insertBatch(config, dataPkg);
        }
        return 0;
    }


}
