package com.atjl.util.collection;

import com.atjl.common.domain.KeyValue;
import com.atjl.util.character.StringCheckUtil;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Collection utils
 *
 * @author JasonLiu798
 * @date 2014/11/20 16:20
 */
public final class CollectionUtil {
    private CollectionUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(CollectionUtil.class);

    /**
     * ############################## check functions ###################################
     */
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
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

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
     * @param collection
     * @return
     */
    public static boolean isExistEmptyString(Collection<String> collection) {
        if (isEmpty(collection)) {
            return true;
        }
        for (String s : collection) {
            if (StringCheckUtil.isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param c collection
     * @param t item
     */
    public static <T> boolean isIn(Collection<T> c, T t) {
        if (isEmpty(c)) {
            return false;
        }
        for (T i : c) {
            if (i.equals(t)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLikeIn(Collection<String> c, String t) {
        if (isEmpty(c)) {
            return false;
        }
        for (String i : c) {
            if (i.indexOf(t) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean existDuplicate(Collection<T> collection) {
        if (isEmpty(collection)) {
            return false;
        }
        if (collection.size() == 1) {
            return false;
        }
        Set<T> set = new HashSet<>();
        for (T t : collection) {
            if (set.contains(t)) {
                return true;
            } else {
                set.add(t);
            }
        }
        return false;
    }

    /**
     * ############################## getter functions ###################################
     */
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
     * get two list's same element
     *
     * @param mainList
     * @param otherList
     * @param <T>
     * @return
     */
    public static <T> List<T> getDuplicateItemUseHash(List<T> mainList, List<T> otherList) {
        if (CollectionUtil.isEmpty(mainList) || CollectionUtil.isEmpty(otherList)) {
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
     *
     * @param mainList
     * @param otherList
     * @param <T>
     * @return
     */
    public static <T> T getOneDuplicateItemUseHash(List<T> mainList, List<T> otherList) {
        List<T> list = getDuplicateItemUseHash(mainList, otherList);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        return list.get(0);
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
     * ############################## filter functions ###################################
     **/


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

    public static <T> T[] set2array(Set<T> set) {
        if (CollectionUtil.isEmpty(set)) {
            return null;//Array.newInstance();
        }
        Iterator<T> it = set.iterator();
        T obj = it.next();
        T[] res = (T[]) Array.newInstance(obj.getClass(), set.size());
        int i = 0;
        for (T item : set) {
            res[i] = item;
            i++;
        }
        return res;
    }


    /**
     * array to arrayList
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> array2List(T[] array) {
        if (CollectionUtil.isEmpty(array)) {
            return null;
        }
        return Arrays.asList(array);
    }

    /**
     * list to array
     *
     * @param list
     * @return
     */
    public static <T> T[] list2array(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        int size = list.size();
        T first = list.get(0);
        Class clz = first.getClass();
        T[] arr = (T[]) Array.newInstance(clz, size);
        return list.toArray(arr);
    }

    public static <T> Set<T> list2set(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        Set<T> res = new HashSet<T>();
        res.addAll(list);
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
        if (CollectionUtil.isEmpty(array)) {
            return null;
        }
        Set<T> s = new HashSet<T>();
        for (T item : array) {
            s.add(item);
        }
        return s;
    }


    /**
     * rm duplicate item
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
        if (CollectionUtil.isEmpty(mainList) || CollectionUtil.isEmpty(deleteList)) {
            return mainList;
        }
        Iterator<T> it = mainList.iterator();
        while (it.hasNext()) {
            T mainItem = it.next();
            for (T deleteItem : deleteList) {
                try {
                    //logger.debug("main {},del {}",mainItem,deleteItem);
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
        if (CollectionUtil.isEmpty(mainList) || CollectionUtil.isEmpty(deleteList)) {
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
            logger.error("reflect method error,method:{}", getMethod);
            logger.error(e.getMessage());
            return mainList;
        }
        return mainList;
    }


    /**
     * ############################## debug functions ###################################
     */
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
                break;
            case 1:
                logger.debug(sb.toString());
                break;
            case 2:
                logger.info(sb.toString());
                break;
            case 3:
                logger.warn(sb.toString());
                break;
            case 4:
                logger.error(sb.toString());
                break;
            default:
                break;
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
        logger.debug("interval:{}", interval);
        int i = 0;
        while (i < totalSize) {
            int st = i;
            int ed = st + interval - 1;
            if (ed > totalSize - 1) {
                ed = totalSize - 1;
            }
            logger.debug("copy:{},{}", st, ed);
            List<T> tmp = copy(list, st, ed);
            if (tmp != null) {
                res.add(tmp);
            }
            i += interval;
        }
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
     * copy map
     * !!not deep copy
     *
     * @param map
     */
    public static <K, V> Map<K, V> copy(Map<K, V> map) {
        if (isEmpty(map)) {
            return new HashMap<>();
        }
        Map<K, V> res = new HashMap<K, V>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            res.put(entry.getKey(), entry.getValue());
        }
        return res;
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
        if (CollectionUtil.isEmpty(list)) {
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

    public static List<String> castInt2String(List<Integer> list) {
        List<String> res = new LinkedList<>();
        for (Integer i : list) {
            res.add(String.valueOf(i));
        }
        return res;
    }

    public static Set<String> string2Set(String str) {
        Set<String> s = new HashSet<>();
        if (StringCheckUtil.isEmpty(str)) {
            return s;
        }
        s.add(str);
        return s;
    }

    public static <K, V> Map<K, V> freeze1(Map<K, V> map, final boolean copy) {
        if (copy) {
            map = new HashMap<>(map);
        }
        return Collections.unmodifiableMap(map);
    }

    /**
     * 注：map value必须为 bean，原始类型不支持
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <K, V> Map<K, V> copyMap(Map<K, V> map) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map nmap = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            nmap.put(entry.getKey(), BeanUtils.cloneBean(entry.getValue()));//entry.getValue().clone());
        }
        return nmap;
    }

    public static String getKeySetString(Map map) {
        int i = 0;
        int last = map.keySet().size() - 1;
        StringBuilder sb = new StringBuilder();
        for (Object key : map.keySet()) {
            if (i != last) {
                sb.append(key + ",");
            } else {
                sb.append(key);
            }
            i++;
        }
        return sb.toString();
    }


    /**
     *
     */
    /**
     * @param el
     * @param <T>
     * @return
     */
    public static <T> List<T> newList(T el) {
        List<T> l = new LinkedList<>();
        l.add(el);
        return l;
    }

    public static <T> List<T> newAList(T el) {
//        Lists.newArrayListWithExpectedSize(100);
//        Lists.newArrayListWithCapacity(100);
        List<T> l = new ArrayList<>();
        l.add(el);
        return l;
    }

    public static <T> List<T> newList(T... els) {
        List<T> l = new LinkedList<>();
        if (els == null || els.length == 0) {
            return l;
        }
        for (T e : els) {
            l.add(e);
        }
        return l;
    }

    public static <T> List<T> newAList(T... els) {
        if (els == null || els.length == 0) {
            return new ArrayList<>();
        }
        List<T> l = new ArrayList<>(els.length);
        for (T e : els) {
            l.add(e);
        }
        return l;
    }

    public static <T> Set<T> newSet(T... els) {
        Set<T> set = new HashSet<>();
        if (els == null || els.length == 0) {
            return set;
        }
        for (T e : els) {
            set.add(e);
        }
        return set;
    }

    public static Map<String, String> newStringMap(String... values) {
        if (CollectionUtil.isEmpty(values)) {
            return new HashMap<>();
        }

        int i = 0;
        int expectSize = values.length / 2;
        Map<String, String> map = Maps.newHashMapWithExpectedSize(expectSize);
        for (; i < values.length; i += 2) {
            String v = null;
            if (i + 1 < values.length) {
                v = values[i + 1];
            }
            map.put(values[i], v);
        }
        return map;
    }

    public static List<KeyValue> newKVL(String... values) {
        if (CollectionUtil.isEmpty(values)) {
            return new ArrayList<>();
        }
        int i = 0;
        List<KeyValue> res = new ArrayList<>();
        for (; i < values.length; i += 2) {
            String v = null;
            if (i + 1 < values.length) {
                v = values[i + 1];
            }
            KeyValue kv = new KeyValue();
            kv.setKey(values[i]);
            kv.setValue(v);
            res.add(kv);
        }
        return res;
    }

    public static <K, V> Map<K, V> newMap(K key, V value) {
        return newMap(key, value, 10);
    }

    public static <K, V> Map<K, V> newMap(K key, V value, int expectSize) {
        Map<K, V> map = Maps.newHashMapWithExpectedSize(expectSize);
        map.put(key, value);
        return map;
    }

    public static <K, V> Map<K, V> addKV(Map<K, V> map, K key, V value) {
        map.put(key, value);
        return map;
    }

    /**
     * @param map
     * @param key true:生成key list， false:生成value list
     * @return
     */
    public static <K, V> List map2list(Map<K, V> map, boolean key) {
        List res = new ArrayList();
        if (CollectionUtil.isEmpty(map)) {
            return res;
        }
        if (key) {
            res.addAll(map.keySet());
        } else {
            res.addAll(map.values());
        }
        return res;
    }


    public static <T> T[] newArr(T... v) {
        return v;
    }


    /**
     * ########################## filters ###########################
     */
    @Deprecated
    public static <T> T[] filterNull(T[] raw) {
        return CollectionFilterUtil.filterNull(raw);//use this
    }


    /**
     * ####################### output #############################
     */
    public static <T> String toStringConsole(Collection<T> arr) {
        if (arr == null || arr.size() <= 0) {
            return "empty collection";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Collection size:").append(arr.size()).append("\n");
        int i = 0;
        for (T o : arr) {
            sb.append(String.format("[%s] %s %n", i, o));
            i++;
        }
        return sb.toString();
    }

}