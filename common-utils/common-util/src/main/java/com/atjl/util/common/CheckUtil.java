package com.atjl.util.common;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;

import java.util.Map;

/**
 * 校验工具类
 *
 * @author jasonliu
 */
public class CheckUtil {
    private CheckUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 存在一个为空 抛异常
     *
     * @param objs
     */
    public static void checkExistNull(Object... objs) {
        if (CollectionUtil.isEmpty(objs)) {
            throw new NullPointerException();
        }
        for (Object obj : objs) {
            if (obj == null) {
                throw new NullPointerException();
            }
        }
    }

    /**
     * 存在一个为空字符串 抛异常
     *
     * @param strs
     */
    public static void checkStrExistNull(String... strs) {
        if (CollectionUtil.isEmpty(strs)) {
            throw new NullPointerException();
        }
        for (String s : strs) {
            if (StringCheckUtil.isEmpty(s)) {
                throw new NullPointerException();
            }
        }
    }

    /**
     * 抛异常:全部为空
     * 不抛异常:至少存在一个不为空
     *
     * @param objs
     */
    public static void checkAllNull(Object... objs) {
        if (CollectionUtil.isEmpty(objs)) {
            throw new NullPointerException();
        }
        boolean existNotNull = false;
        for (Object obj : objs) {
            if (obj != null) {
                existNotNull = true;
            }
        }
        if (!existNotNull) {
            throw new NullPointerException();
        }
    }

    public static void checkMapExistNullAndThrow(Map<String, String> map) {
        if (checkMapExistNull(map)) {
            throw new NullPointerException();
        }
    }

    public static boolean checkMapExistNull(Map<String, String> map) {
        if (CollectionUtil.isEmpty(map)) {
            return true;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringCheckUtil.isEmpty(entry)) {
                return true;
            }
        }
        return false;
    }
}
