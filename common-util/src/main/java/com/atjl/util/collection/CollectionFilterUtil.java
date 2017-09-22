package com.atjl.util.collection;


import com.atjl.util.common.BlackWhiteUtil;
import com.atjl.util.common.domain.BlackWhiteResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CollectionFilterUtil {
    private CollectionFilterUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(CollectionFilterUtil.class);

    /**
     * filter list 2 count,drop the node index >= count
     *
     * @param list
     * @param count
     * @return
     */
    public static <T> List<T> filterList2Size(List<T> list, int count) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size <= count) {
            return list;
        } else {
            int start = count - 1;
            int end = list.size() - 1;
            if (logger.isDebugEnabled()) {
                logger.debug("start " + start + " end " + end);
            }
            for (int i = end; i > start; i--) {
                list.remove(i);
            }
        }
        return list;
    }

    public static <T> Map<String, T> filterMap(Map<String, T> map, String[] filterKeys) {
        return filterMap(map, CollectionUtil.array2List(filterKeys));
    }

    /**
     * map过滤，只过滤黑名单，key为String的
     * 注：直接修改原map
     *
     * @param map
     * @param filterKeys void
     */
    public static <T> Map<String, T> filterMap(Map<String, T> map, List<String> filterKeys) {
        if (CollectionUtil.isEmpty(filterKeys) || CollectionUtil.isEmpty(map)) {
            return new HashMap<>();
        }
        Map<String, T> res = CollectionUtil.copy(map);
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
        if (CollectionUtil.isEmpty(map)) {
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


    /**
     * filter duplicate element in list
     *
     * @param l
     * @return
     */
    public static List<String> filterDuplicate(List<String> l) {
        if (CollectionUtil.isEmpty(l)) {
            return l;
        }
        Set<String> set = new HashSet<>();
        for (String s : l) {
            set.add(s);
        }
        List<String> res = new LinkedList<>();
        res.addAll(set);
        return res;
    }


    /**
     * @param raw
     * @param <T>
     * @return
     */
    public static <T> T[] filterNull(T[] raw) {
        if (CollectionUtil.isEmpty(raw)) {
            return raw;
        }
        List<T> n = new ArrayList<T>(raw.length);
        for (T t : raw) {
            if (t != null) {
                n.add(t);
            }
        }
        return CollectionUtil.list2array(n);
    }


    public static List<String> filterDelListExistStr(List<String> tgtList, List<String> delList) {
        return filterDelListInner(tgtList, delList, CollectionConstant.TP_EXIST_STR);
    }


    /**
     * 删除tgtlist中所有存在于dellist的item
     */
    public static <T> List<T> filterDelList(List<T> tgtList, List<T> delList) {
        return filterDelListInner(tgtList, delList, CollectionConstant.TP_EQ);
    }

    /**
     * @param tgtList
     * @param delList
     * @param option  1,list  must be String,use index of
     * @return
     */
    public static <T> List<T> filterDelListInner(List<T> tgtList, List<T> delList, int option) {
        if (CollectionUtil.isEmpty(delList)) {
            return tgtList;
        }
        if (CollectionUtil.isEmpty(tgtList)) {
            return tgtList;
        }
        List<T> res = new ArrayList<>(tgtList.size());
        for (T tgtItem : tgtList) {
            boolean gotEq = false;
            if (option == CollectionConstant.TP_EQ) {
                gotEq = CollectionUtil.isIn(delList, tgtItem);
            } else if (option == CollectionConstant.TP_EXIST_STR) {
                gotEq = CollectionUtil.isLikeIn((List<String>) delList, tgtItem.toString());
            }

            if (gotEq) {
                continue;
            } else {
                res.add(tgtItem);
            }
        }
        return res;
    }

}
