package com.atjl.dbservice.util;


import com.atjl.common.constant.CommonConstant;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataFilterUtil {

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
