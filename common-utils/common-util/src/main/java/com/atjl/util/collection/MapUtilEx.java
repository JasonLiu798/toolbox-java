package com.atjl.util.collection;

import com.atjl.util.common.BlackWhiteUtil;
import com.atjl.util.common.domain.BlackWhiteResp;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MapUtilEx {

    /**
     * check map empty
     *
     * @param map map
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }


    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }


    /**
     * copy map
     * !!not deep copy
     *
     * @param map
     */
    public static <K, V> Map<K, V> copy(Map<K, V> map) {
        if (MapUtilEx.isEmpty(map)) {
            return new HashMap<>();
        }
        Map<K, V> res = new HashMap<K, V>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            res.put(entry.getKey(), entry.getValue());
        }
        return res;
    }

    public static <T> Map<String, T> filterMap(Map<String, T> map, String[] filterKeys) {
        return filterMap(map, CollectionUtilEx.array2List(filterKeys));
    }

    /**
     * map过滤，只过滤黑名单，key为String的
     * 注：直接修改原map
     *
     * @param map
     * @param filterKeys void
     */
    public static <T> Map<String, T> filterMap(Map<String, T> map, List<String> filterKeys) {
        if (CollectionUtilEx.isEmpty(filterKeys) || isEmpty(map)) {
            return new HashMap<>();
        }
        Map<String, T> res = copy(map);
        for (String key : filterKeys) {
            res.remove(key);
        }
        return res;
    }


    /**
     * map过滤
     *
     * @param map
     * @param balckList
     * @param whiteList
     * @return
     */
    public static <K, V> Map<K, V> filterMap(Map<K, V> map, List<K> balckList, List<K> whiteList) {
        if (isEmpty(map)) {
            return map;
        }
        BlackWhiteResp resp = BlackWhiteUtil.processBlackWhite(whiteList, balckList);
        Map<K, V> resMap = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (resp.isUseBlack() &&
                    resp.getBlack().indexOf(entry.getKey()) >= 0) {
                continue;
            }
            //process white list
            if (resp.isUseWhite() &&
                    whiteList.indexOf(entry.getKey()) < 0) {
                continue;
            }
            resMap.put(entry.getKey(), entry.getValue());
        }
        return map;
    }



    public MapUtilEx() {
        throw new UnsupportedOperationException();
    }
}
