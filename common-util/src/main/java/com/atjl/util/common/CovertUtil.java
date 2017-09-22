package com.atjl.util.common;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * string covert to java types
 * support type: in SUPPORT_CLZ
 *
 * @author jasonliu
 */
public class CovertUtil {
    private CovertUtil() {
        super();
    }

    private static final Logger logger = LoggerFactory.getLogger(CovertUtil.class);

    /**
     * 支持的类型
     */
    private static final Set<Class> SUPPORT_CLZ = new HashSet<>();
    /**
     * 数字类型
     */
    private static final Set<Class> NUMBER_CLZ = new HashSet<>();
    /**
     * 原始数字类型
     */
    private static final Map<Class, Class> NUMBER_RAW_CLZ = new HashMap<>();

    public static final String DFT_SEP = ",";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";

    static {
        SUPPORT_CLZ.add(String.class);
        SUPPORT_CLZ.add(Long.class);
        SUPPORT_CLZ.add(long.class);
        SUPPORT_CLZ.add(Integer.class);
        SUPPORT_CLZ.add(int.class);
        SUPPORT_CLZ.add(Short.class);
        SUPPORT_CLZ.add(short.class);
        SUPPORT_CLZ.add(Byte.class);
        SUPPORT_CLZ.add(byte.class);
        SUPPORT_CLZ.add(Double.class);
        SUPPORT_CLZ.add(double.class);
        SUPPORT_CLZ.add(Float.class);
        SUPPORT_CLZ.add(float.class);
        SUPPORT_CLZ.add(String[].class);
        SUPPORT_CLZ.add(Boolean.class);
        SUPPORT_CLZ.add(boolean.class);

        NUMBER_CLZ.add(Long.class);
        NUMBER_CLZ.add(long.class);
        NUMBER_CLZ.add(Integer.class);
        NUMBER_CLZ.add(int.class);
        NUMBER_CLZ.add(Short.class);
        NUMBER_CLZ.add(short.class);
        NUMBER_CLZ.add(Byte.class);
        NUMBER_CLZ.add(byte.class);
        NUMBER_CLZ.add(Double.class);
        NUMBER_CLZ.add(double.class);
        NUMBER_CLZ.add(Float.class);
        NUMBER_CLZ.add(float.class);

        NUMBER_RAW_CLZ.put(long.class, Long.class);
        NUMBER_RAW_CLZ.put(int.class, Integer.class);
        NUMBER_RAW_CLZ.put(short.class, Short.class);
        NUMBER_RAW_CLZ.put(byte.class, Byte.class);
        NUMBER_RAW_CLZ.put(double.class, Double.class);
        NUMBER_RAW_CLZ.put(float.class, Float.class);
    }

    public static Class<?> prim2box(Class<?> c) {
        if (c == null) {
            return null;
        }
        if (NUMBER_RAW_CLZ.containsKey(c)) {
            return NUMBER_RAW_CLZ.get(c);
        }
        return c;
    }


    /**
     * ################ obj to str covert to target class ################
     */
    public static <T> T covertObj(Object rawVal, Class<T> tgtClz) {
        return covertObj(rawVal, tgtClz, null, DFT_SEP);
    }
    public static <T> T covertObj(Object rawVal, Class<T> tgtClz, T dft) {
        return covertObj(rawVal, tgtClz, dft, DFT_SEP);
    }

    public static <T> T covertObj(Object rawVal, Class<T> tgtClz, T dft, String sep) {
        if (rawVal == null) {
            return covert(null, tgtClz, dft, sep);
        } else {
            return covert(rawVal.toString(), tgtClz, dft, sep);
        }
    }


    /**
     * ################ str covert to target class ################
     */
    public static <T> T covert(String rawVal, Class<T> tgtClz) {
        return covert(rawVal, tgtClz, null, DFT_SEP);
    }

    public static <T> T covert(String rawVal, Class<T> tgtClz, T dft) {
        return covert(rawVal, tgtClz, dft, DFT_SEP);
    }

    public static <T> T covert(String rawVal, Class<T> tgtClz, T dft, String sep) {
        if (tgtClz == String.class) {
            return (T) rawVal;
        }

        if (!SUPPORT_CLZ.contains(tgtClz)) {
            if (logger.isInfoEnabled()) {
                logger.info("config covert unsupport clz,raw {},tgt {},dft {}", rawVal, tgtClz, dft);
            }
            return dft;
        }
        if (StringCheckUtil.isEmpty(rawVal)) {
            if (logger.isInfoEnabled()) {
                logger.info("config covert null,raw {},tgt {},dft {}", rawVal, tgtClz, dft);
            }
            return dft;
        }
        if (NUMBER_CLZ.contains(tgtClz)) {
            if (NUMBER_RAW_CLZ.containsKey(tgtClz)) {
                tgtClz = NUMBER_RAW_CLZ.get(tgtClz);
            }
            String func = genParseFuncName(tgtClz);
            try {
                Method m = tgtClz.getDeclaredMethod("parse" + func, String.class);
                Object res = m.invoke(rawVal, rawVal);
                return (T) res;
            } catch (Exception e) {
                logger.error("str to num fail,raw {},clz {},{}", rawVal, tgtClz, e.getMessage());
                return dft;
                //throw new UtilException("str to number fail,not match,raw " + rawVal);
            }
        } else if (tgtClz == Boolean.class || tgtClz == boolean.class) {
            Object res = true;
            if (FALSE.equals(rawVal.toUpperCase())) {
                res = false;
            } else if (TRUE.equals(rawVal.toUpperCase())) {
                res = true;
            } else {
                logger.error("str to boolean fail,not match,raw {}", rawVal);
                return dft;
                //throw new UtilException("str to boolean fail,not match,raw " + rawVal);
            }
            return (T) res;
        } else if (tgtClz == String[].class) {
            String[] res = rawVal.split(sep);
            if (CollectionUtil.isEmpty(res)) {
                return dft;
            } else {
                return (T) res;
            }
        }
        //throw new UtilException("unsupported type " + tgtClz);//never reached
        logger.error("unsupported type {}", tgtClz);
        return dft;
    }


    /**
     * 生成各个类的 parse
     *
     * @param clz
     * @return
     */
    private static String genParseFuncName(Class clz) {
        String func = StringUtil.toUpperCaseFirstOne(clz.getSimpleName());
        if (clz == Integer.class) {
            func = func.substring(0, 3);//parseInt
        }
        return func;
    }


}
