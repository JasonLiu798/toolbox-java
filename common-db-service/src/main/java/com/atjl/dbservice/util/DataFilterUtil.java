package com.atjl.dbservice.util;


import com.atjl.common.constant.CommonConstant;
import com.atjl.dbservice.api.domain.DataCpConfig;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFilterUtil {


    /**
     * 内存过滤重复数据
     */
    public static List<Map> filterDuplicate(DataCpConfig config, List<Map> l) {
        if (CollectionUtil.isEmpty(l)) {
            return l;
        }
        List<Map> res = new ArrayList<>();
        Map<String, Map> noDuplicateMap = new HashMap<>();
        for (Map item : l) {
            String pk = DataFieldUtil.getPkValues(item, config);
            Map existItem = noDuplicateMap.get(pk);
            if (existItem != null) {
                if (config.getRawDataDuplicateCheck().keepWhich(existItem, item)) {
                    item = existItem;
                }
            }
            noDuplicateMap.put(pk, item);
        }
        for (Map.Entry<String, Map> entry : noDuplicateMap.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }


    public static boolean loadTmDftChecker(Map raw, Map tgt) {
        String rawStr = StringUtil.getEmptyString(raw.get("load_tm"));
        String tgtStr = StringUtil.getEmptyString(tgt.get("LOAD_TM"));
        if (rawStr.compareTo(tgtStr) <= 0) {//原始表值 小于 目标表值，不更新
            return false;
        }
        return true;
    }

    public static boolean isModifyChecker(Map tgt) {
        String modify = StringUtil.getEmptyString(tgt.get("IS_MODIFY"));
        if (StringCheckUtil.equal(modify, CommonConstant.YES)) {
            return false;
        }
        return true;
    }


    public static boolean isAllEqual(Map<String, String> pkMapping, Map raw, Map tgt) {
        List<Boolean> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : pkMapping.entrySet()) {
            boolean find = false;
            if (StringCheckUtil.equal(String.valueOf(raw.get(entry.getKey())), String.valueOf(tgt.get(entry.getValue())))) {
                find = true;
            }
            result.add(find);
        }
        boolean existNotEqual = false;
        for (Boolean r : result) {
            if (!r) {
                existNotEqual = true;
            }
        }
        return !existNotEqual;
    }

    /**
     * 过滤 load_tm 小于等于的
     *
     public static boolean canUpdate(Map rawData, Map tgtData, DbTableTransferConfig config) {
     Map<String, String> cols = config.getNoUpdateCheckMapping();
     if (CollectionUtil.isEmpty(cols)) {
     return true;
     }
     for (Map.Entry<String, String> fc : cols.entrySet()) {
     String rawCol = fc.getKey();
     String tgtCol = fc.getValue();
     String rawStr = StringUtil.getEmptyString(rawData.get(rawCol));
     String tgtStr = StringUtil.getEmptyString(tgtData.get(tgtCol));
     if (rawStr.compareTo(tgtStr) <= 0) {//原始表值 小于 目标表值，不更新
     return false;
     }
     }
     return true;
     }*/

}
