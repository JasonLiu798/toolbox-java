package com.jason798.collection;

import com.jason798.character.StringCheckUtil;
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

    private static final Logger LOG = LoggerFactory.getLogger(CollectionHelper.class);


    /**
     * check array is null or size == 0
     *
     * @param arr array
     * @return null or not
     */
    public static <T> boolean isEmpty(T[] arr) {
        if (arr == null || arr.length <= 0) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }

    /**
     * check collection null or size == 0
     *
     * @param collection collection
     * @return null or not
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }


    public static <K, V> boolean isEmpty(Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * get idx-1 's node
     *
     * @param list list
     * @param idx  index
     * @return the (idx-1)'s node
     */
    public static <T> T getPreNode(List<T> list, int idx) {
        if (idx <= 1 || idx > list.size() - 1) {
            return null;
        }
        return list.get(idx - 1);
    }

    /**
     * get idx+1 's node
     *
     * @param list list
     * @param idx  index
     * @return the (idx+1)'s node
     */
    public static <T> T getNextNode(List<T> list, int idx) {
        if (idx < 0 || idx >= list.size() - 1) {
            return null;
        }
        return list.get(idx + 1);
    }


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
            if (LOG.isDebugEnabled()) {
                LOG.debug("start " + start + " end " + end);
            }
            for (int i = end; i > start; i--) {
                list.remove(i);
            }
        }
        return list;
    }

    /**
     * remove filterKeys from map,
     * thread-safe depend on map
     *
     * @param map
     * @param filterKeys void
     */
    public static <T> void filterMap(Map<String, T> map, String[] filterKeys) {
        if (isEmpty(filterKeys) || isEmpty(map)) {
            return;
        }
        for (String key : filterKeys) {
            map.remove(key);
        }
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
     * array to arrayList
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> array2List(T[] array) {
        if (CollectionHelper.isEmpty(array)) {
            return null;
        }
        List<T> res = new ArrayList<>(array.length);
        Collections.addAll(res, array);
        return res;
    }

    /**
     * array to hashset
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> Set<T> array2Set(T[] array) {
        if (CollectionHelper.isEmpty(array)) {
            return null;
        }
        Set<T> s = new HashSet<T>();
        for (T item : array) {
            s.add(item);
        }
        return s;
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
                    //LOG.debug("main {},del {}",mainItem,deleteItem);
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
            LOG.error("reflect method error,method:{}", getMethod);
            LOG.error(e.getMessage());
            return mainList;
        }
        return mainList;
    }

    /**
     * get two list's same element
     * @param mainList
     * @param otherList
     * @param <T>
     * @return
     */
    public static <T> List<T> getDuplicateItemUseHash(List<T> mainList, List<T> otherList) {
        if (CollectionHelper.isEmpty(mainList) || CollectionHelper.isEmpty(otherList)) {
            return new LinkedList<>();
        }
        Set otherSet = new HashSet();
        List<T> res = new LinkedList<>();
        for (T item : otherList) {
            otherSet.add(item);
        }
        for (T item : mainList) {
            if (otherSet.contains(item)) {
                res.add(item);
            }
        }
        return res;
    }

    /**
     * get the first duplicate item
     * @param mainList
     * @param otherList
     * @param <T>
     * @return
     */
    public static <T> T getOneDuplicateItemUseHash(List<T> mainList,List<T> otherList){
        List<T> list = getDuplicateItemUseHash(mainList,otherList);
        if(CollectionHelper.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }


    public static <T> void printList(Collection<T> c) {
        printList(c, 0);
    }

    public static <T> void printList(Collection<T> c, String name) {
        printList(c, name, 0);
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
        StringBuilder sb = new StringBuilder();
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
                System.out.println(sb.toString());
            case 1:
                LOG.debug(sb.toString());
                break;
            case 2:
                LOG.info(sb.toString());
                break;
            case 3:
                LOG.warn(sb.toString());
                break;
            case 4:
                LOG.error(sb.toString());
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
        LOG.debug("interval:{}", interval);
        int i = 0;
        while (i < totalSize) {
            int st = i;
            int ed = st + interval - 1;
            if (ed > totalSize - 1) {
                ed = totalSize - 1;
            }
            LOG.debug("copy:{},{}", st, ed);
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


    public static Object resizeArray(Object oldArray, int newSize) {
        int oldSize = java.lang.reflect.Array.getLength(oldArray);
        Class elementType = oldArray.getClass().getComponentType();
        Object newArray = java.lang.reflect.Array.newInstance(
                elementType, newSize);
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength > 0)
            System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        return newArray;
    }


    /**
     * remove list 's first n element
     *
     * @param list list
     * @param n    n to remove
     * @return after removed list
     */
    public static <T> List<T> removeListFirstN(List<T> list, int n) {
        if (CollectionHelper.isEmpty(list)) {
            return list;
        }
        if (list.size() < n) {
            return list;
        }
        Iterator<T> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext() && i < n) {
            iterator.next();
            iterator.remove();
            i++;
        }
        return list;
    }


    public static List<String> castInt2String(List<Integer> list){
        List<String> res = new LinkedList<>();
        for(Integer i:list){
            res.add(String.valueOf(i));
        }
        return res;
    }


    public static Set<String> string2Set(String str){
        Set<String> s = new HashSet<>();
        if(StringCheckUtil.isEmpty(str)){
            return s;
        }
        s.add(str);
        return s;
    }

//    public static <T> T[] list2array(List<T> l){
//
//        T[] = new T[l.size()];
//    }
}