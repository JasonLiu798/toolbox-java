package com.jason798.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 集合辅助类
 *
 * @author JasonLiu798
 * @date 2014/11/20 16:20
 */
public final class CollectionHelper {

    private static final Logger log = LoggerFactory.getLogger(CollectionHelper.class);

    /**
     * 判断 collection是否为空
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断collection 不为空
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        if (collection != null && collection.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取 idx 前节点
     *
     * @param list
     * @param idx
     * @param <T>
     * @return
     */
    public static <T> T getPreNode(List<T> list, int idx) {
        if (idx <= 0 || idx > list.size() - 1) {
            return null;
        }
        return list.get(idx - 1);
    }

    /**
     * 获取 idx 后一个节点
     *
     * @param list
     * @param idx
     * @param <T>
     * @return
     */
    public static <T> T getNextNode(List<T> list, int idx) {
        if (idx < 0 || idx >= list.size() - 1) {
            return null;
        }
        return list.get(idx + 1);
    }


    /**
     * 删除list中超过count的item
     *
     * @param list
     * @param count
     * @param <T>
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
            log.debug("start " + start + " end " + end);
            for (int i = end; i > start; i--) {
                list.remove(i);
            }
        }
        return list;
    }

    /**
     * 删除tgtlist中所有存在于dellist的item
     *
     * @param tgtList
     * @param delList
     * @return
     */
    public static <T> List<T> filterDelList(List<T> tgtList, List<T> delList) {
        if (isEmpty(delList)) {
            return tgtList;
        }
        if (isEmpty(tgtList)) {
            return tgtList;
        }
        Iterator<T> tgtIterator = tgtList.iterator();
        while (tgtIterator.hasNext()) {
            T tgt = tgtIterator.next();
            for (T item : delList) {
                if (item.equals(tgt)) {
                    tgtIterator.remove();
                }
            }
        }
        return tgtList;
    }

    /**
     * set转为list
     *
     * @param set
     * @param <T>
     * @return
     */
    public static <T> List<T> set2List(Set<T> set) {
        List<T> list = new ArrayList<>(set.size());
        list.addAll(set);
        return list;
    }


    /**
     * delete duplicate item
     * algorithm:
     * two loop
     * mainList length = n
     * deleteList length = m
     * time cost O(n*m)
     * space cost O(n+m)
     *
     * @param mainList
     * @param deleteList
     * @param <T>
     * @return
     */
    public static <T> List<T> deleteDuplicatItemLoop(List<T> mainList, List<T> deleteList, Method checkMethod) {
        if (CollectionHelper.isEmpty(mainList) || CollectionHelper.isEmpty(deleteList)) {
            return mainList;
        }
        Iterator<T> it = mainList.iterator();
        while (it.hasNext()) {
            T mainItem = it.next();
            for (T deleteItem : deleteList) {
                try {
                    //log.debug("main {},del {}",mainItem,deleteItem);
                    Boolean res = (Boolean) checkMethod.invoke(mainItem, deleteItem);
                    if (res != null && res == true) {
                        it.remove();
                        break;
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return mainList;
    }

    /**
     * 删除重复元素
     * mainList length = n
     * deleteList length = m
     * time cost O(n+m)
     * space cost O(n+m)
     *
     * @param mainList
     * @param deleteList
     * @param <T>
     * @return
     */
    public static <T> List<T> deleteDuplicatItemHash(List<T> mainList, List<T> deleteList, Method getMethod) {
        if (CollectionHelper.isEmpty(mainList) || CollectionHelper.isEmpty(deleteList)) {
            return mainList;
        }
        Map deleteMap = new HashMap();
        try {
            for (T item : deleteList) {
                Object key = getMethod.invoke(item);
                deleteMap.put(key, item);
            }
            Iterator<T> it = mainList.iterator();
            while (it.hasNext()) {
                T item = it.next();
                Object key = getMethod.invoke(item);
                Object mapItem = deleteMap.get(key);
                if (mapItem != null) {
                    it.remove();
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("reflect method error,method:{}", getMethod);
            log.error(e.getMessage());
            return mainList;
        }
        return mainList;
    }


    public static <T> void printList(Collection<T> c) {
        printList(c, 0);
    }
    public static <T> void printList(Collection<T> c, String name) {
        printList(c, name,0);
    }
    public static <T> void printList(Collection<T> c, int level) {
        printList(c, null, level);
    }
    /**
     * for debug
     *
     * @param
     * @param <T>
     */
    public static <T> void printList(Collection<T> c, String name, int level) {
        if (name == null) {
            name = "default";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("Collection:{[");
        sb.append(name);
        sb.append("]\n");
        Iterator<T> it = c.iterator();
        int i = 0;
        while (it.hasNext()) {
            T item = it.next();
            sb.append(i + "," + item + "\n");
            i++;
        }
        sb.append("}\n");
        switch (level) {
            case 0:
                log.debug(sb.toString());
                break;
            case 1:
                log.info(sb.toString());
                break;
            case 2:
                log.warn(sb.toString());
                break;
            case 3:
                log.error(sb.toString());
        }
    }

    /**
     * separate list to small list,and size = singleListSize,if not
     *
     * @param list
     * @param interval
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> separateList(List<T> list, int interval) {
        List<List<T>> res = new LinkedList<>();
        if (isEmpty(list)) {
            return res;
        }
        int totalSize = list.size();
        if (interval >= totalSize || interval <= 0) {
            List<T> tmp = copy(list);
            res.add(tmp);
            return res;
        }
//        Integer interval = getInterval(totalSize,singleListSize);
        log.debug("interval:{}", interval);
        int i = 0;
        while (i < totalSize) {
            int st = i;
            int ed = st + interval - 1;
            if (ed > totalSize - 1) {
                ed = totalSize - 1;
            }
            log.debug("copy:{},{}", st, ed);
            List<T> tmp = copy(list, st, ed);
            if (tmp != null) {
                res.add(tmp);
            }
            i += interval;
        }
        return res;
    }

    /**
     * get int interval
     *
     * @param totalSize
     * @param singleSize
     * @return
     */
    public static Integer getInterval(Integer totalSize, Integer singleSize) {
        float totalSizeF = totalSize;
        float singleSizeF = singleSize;
        float resF = totalSizeF / singleSizeF;
        Integer res = Math.round(resF);
        return res;
    }

    /**
     * copy list all
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> copy(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return copy(list, 0, list.size());
    }

    /**
     * copy list start to end
     *
     * @param list
     * @param start
     * @param end
     * @param <T>
     * @return null, if
     */
    public static <T> List<T> copy(List<T> list, int start, int end) {
        if (isEmpty(list)) {
            return null;
        }
        List<T> res = new LinkedList<>();
        int size = list.size();
        if (start > size || start > end) {
            return res;
        }
        if (end > size - 1) {
            end = size - 1;
        }
        for (int i = start; i <= end; i++) {
            res.add(list.get(i));
        }
        return res;
    }


    /**
     * python range
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> range(int start,int end){
        return range(start,end,1);
    }

    /**
     * python range
     * @param start
     * @param end
     * @param interval
     * @return
     */
    public static List<Integer> range(int start,int end,int interval){
        if(end<start ){
            return null;
        }
        if(interval<=0){
            interval = 1;
        }
        int len = end-start;
        int add = Math.min(Math.max(len%interval,0),1);
        int size = len/interval+add;
        log.debug("range size :{}, / {}, % {}",size,len/interval,add);
        List<Integer> list = new ArrayList<>(size);
        for(int i = start;i<end;i+=interval){
            list.add(i);
        }
        return list;
    }


//    public static <T> T[] list2array(List<T> l){
//
//        T[] = new T[l.size()];
//    }
}