package com.atjl.util.collection;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CollectionFilterUtil {


    /**
     * cut list to specified size, drop the node index >= count
     *
     * @param list list
     * @param count hold count
     * @return new short list
     */
    public static <T> List<T> cutList2Size(List<T> list, int count) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size <= count) {
            return Lists.newArrayList(list);
        } else {
            List<T> res = Lists.newArrayList();
            int start = count - 1;
            int end = list.size() - 1;
            if (log.isDebugEnabled()) {
                log.debug("start " + start + " end " + end);
            }
            for (int i = 0; i<count;i++)  {
                res.add(list.get(i));
            }
            return res;
        }
    }


    /**
     * filter duplicate element in list
     *
     * @param l
     * @return
     */
    public static List<String> filterDuplicate(List<String> l) {
        if (CollectionUtilEx.isEmpty(l)) {
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
        if (CollectionUtilEx.isEmpty(raw)) {
            return raw;
        }
        List<T> n = new ArrayList<T>(raw.length);
        for (T t : raw) {
            if (t != null) {
                n.add(t);
            }
        }
        return CollectionUtilEx.list2array(n);
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
        if (CollectionUtilEx.isEmpty(delList)) {
            return tgtList;
        }
        if (CollectionUtilEx.isEmpty(tgtList)) {
            return tgtList;
        }
        List<T> res = new ArrayList<>(tgtList.size());
        for (T tgtItem : tgtList) {
            boolean gotEq = false;
            if (option == CollectionConstant.TP_EQ) {
                gotEq = CollectionUtilEx.isIn(delList, tgtItem);
            } else if (option == CollectionConstant.TP_EXIST_STR) {
                gotEq = CollectionUtilEx.isLikeIn((List<String>) delList, tgtItem.toString());
            }

            if (gotEq) {
                continue;
            } else {
                res.add(tgtItem);
            }
        }
        return res;
    }

    private CollectionFilterUtil() {
        throw new UnsupportedOperationException();
    }

}
