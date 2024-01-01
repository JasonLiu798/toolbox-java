package com.atjl.util.config;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionFilterUtil;
import com.atjl.util.collection.CollectionUtilEx;
import com.atjl.util.collection.MapUtilEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * int类型 转 boolean list
 * <p>
 * Created by JasonLiu798 on 16/5/31.
 */
public class ConfigIntParser {
    private ConfigIntParser() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(ConfigIntParser.class);

    /**
     * "1101"
     * => 13
     *
     * @param binary
     * @return
     */
    public static int binaryString2Int(String binary) {
        int res = 0;
        if (StringCheckUtil.isEmpty(binary)) {
            return res;
        }
        byte[] bytes = binary.getBytes();
        int mask = 1;
        for (byte binaryByte : bytes) {
            if (binaryByte != '0') {
                res = res | mask;
            }
            mask = mask << 1;
        }
        return res;
    }


    /**
     * 注：增加参数后，旧值需要手动维护
     *
     * @param map
     * @return
     */
    public static int boolMap2Int(Map<String, Boolean> map) {
        if (MapUtilEx.isEmpty(map)) {
            return 0;
        }
        if (map.size() > 32) {
            throw new IllegalArgumentException("int最大支持32个参数");
        }
        TreeMap<String, Boolean> treeMap = new TreeMap<>();
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
        }

        Map.Entry<String, Boolean> entry;
        List<Boolean> blist = new ArrayList<>();
        do {
            entry = treeMap.pollFirstEntry();
            if (entry != null) {
                blist.add(entry.getValue());
            }
        } while (entry != null);
        return booleanList2Int(blist);
    }

    public static Map<String, Boolean> int2boolMap(int opt, List<String> keys) {
        if (CollectionUtilEx.isEmpty(keys)) {
            return new HashMap<>();
        }
        //过滤重复
        keys = CollectionFilterUtil.filterDuplicate(keys);
        if (CollectionUtilEx.isEmpty(keys)) {
            return new HashMap<>();
        }

        //排序
        TreeMap<String, Boolean> treeMap = new TreeMap<>();
        for (String key : keys) {
            treeMap.put(key, false);
        }

        //转换
        List<Boolean> optList = int2bits(opt, keys.size());

        //按排序顺序放值
        Map<String, Boolean> res = new HashMap<>();
        Map.Entry<String, Boolean> entry;
        int i = 0;
        do {
            entry = treeMap.pollFirstEntry();
            if (entry != null) {
                res.put(entry.getKey(), optList.get(i));
                i++;
            }
        } while (entry != null && i <= optList.size());
        return res;
    }


    /**
     * true,false,true
     * =>5
     *
     * @param list
     * @return
     */
    public static int booleanList2Int(List<Boolean> list) {
        if (CollectionUtilEx.isEmpty(list)) {
            return 0;
        }
        if (list.size() > 32) {
            throw new IllegalArgumentException("最大支持32个参数");
        }
//        int len = list.size();
//        if (list.size() > 32) {
//            len = 32;
//        }
        int res = 0;
        int mask = 1;
        for (Boolean item : list) {
            if (item) {
                res = res | mask;
            }
            mask = mask << 1;
        }
        return res;
    }

    /**
     * 12,4
     * =>1100
     * =>true,true,false,false
     *
     * @param configNum
     * @return
     */
    public static List<Boolean> int2bits(int configNum, int size) {
        List<Boolean> res = new ArrayList<>(size);
        if (size < 0) {
            return res;
        }
        int max = (int) Math.pow(2, size) - 1;
//        if (configNum < 0) {
//            throw new IllegalArgumentException("config num must > 0,config num " + configNum);
//        }
        if (configNum > max && logger.isWarnEnabled()) {
            logger.warn("config num {} out of range 0~{},will only use the low bit", configNum, max);
        }
        int mask = 1;
        for (int i = 0; i < size; i++) {
            int bitVal = mask & configNum;
            if (bitVal == 0) {
                res.add(false);
            } else {
                res.add(true);
            }
            mask = mask << 1;
        }
        return res;
    }


}
