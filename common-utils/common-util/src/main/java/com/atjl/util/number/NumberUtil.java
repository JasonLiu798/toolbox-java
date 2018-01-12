package com.atjl.util.number;

import com.atjl.common.api.exception.ParamFormatException;
import com.atjl.util.character.StringCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * number tools
 *
 * @author JasonLiu798
 * @date 2013/2/26 11:43
 */
public class NumberUtil {
    private NumberUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(NumberUtil.class);

    public static final String DOT = ".";

    private static String[] NUM_CN = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static String[] NUM_CARRY_CN = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};


    /**
     * string to int
     *
     * @param raw
     * @return
     */
    public static Integer str2int(String raw) {
        try {
            return Integer.parseInt(raw);
        } catch (Exception e) {
            logger.error("str to int fail,{}", e.getMessage());
            return null;
        }
    }

    public static String toChinese(Integer input) {
        if (input == null || input < 0) {
            throw new ParamFormatException();
        }
        String s = String.valueOf(input);
        if (StringCheckUtil.isEmpty(input)) {
            return "";
        }
        String result = "";
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += NUM_CN[num] + NUM_CARRY_CN[n - 2 - i];
            } else {
                result += NUM_CN[num];
            }
//            System.out.println("  "+result);
        }
//        System.out.println("----------------");
//        System.out.println(result);
        return result;

    }

    /**
     * string to long
     *
     * @param raw
     * @return
     */
    public static Long str2long(String raw) {
        try {
            return Long.parseLong(raw);
        } catch (Exception e) {//str format exception
            logger.error("str to long fail,{}", e.getMessage());
            return null;
        }
    }

    /**
     * long to int
     *
     * @param lnum
     * @return
     * @throws NumberFormatException
     */
    public static int long2Int(long lnum) throws NumberFormatException {
        if (lnum > Integer.MAX_VALUE || lnum < Integer.MIN_VALUE) {
            throw new NumberFormatException();//out of Integer range
        }
        int inum = Integer.parseInt(String.valueOf(lnum));
        return inum;
    }

    /**
     * filter number after dot,if number is decimals ,filter to 0
     * 123 => 123
     * 1.233 = > 1
     * 0.33434 => 0
     *
     * @param number
     * @return
     */
    public static String filterDot(String number) {
        int dotidx = number.indexOf(DOT);
        String res = number;
        if (dotidx > 0) {
            res = number.substring(0, dotidx);
        }
        if (res == null || res.length() == 0) {
            res = "0";
        }
        return res;
    }


    public static <T extends Number> T minus(T t1, T t2) throws Exception {
        Object tmp = null;
        if (t1 instanceof Long && t2 instanceof Long) {
            tmp = (Long) t1 - (Long) t2;
        } else if (t1 instanceof Integer && t2 instanceof Integer) {
            tmp = (Integer) t1 - (Integer) t2;
        } else {
            throw new Exception("Cant use minus for the type!");
        }
        return (T) tmp;
    }

    public static <T extends Number> T incr(T t1) throws Exception {
        Object tmp = null;
        if (t1 instanceof Long) {
            tmp = (Long) t1 + 1;
        } else if (t1 instanceof Integer) {
            tmp = (Integer) t1 + 1;
        } else {
            throw new Exception("Cant use minus for the type!");
        }
        return (T) tmp;
    }

    public static <T extends Number> T add(T t1, T t2) throws Exception {
        Object tmp = null;
        if (t1 instanceof Long && t2 instanceof Long) {
            tmp = (Long) t1 + (Long) t2;
        } else if (t1 instanceof Integer && t2 instanceof Integer) {
            tmp = (Integer) t1 + (Integer) t2;
        } else {
            throw new Exception("Cant use sum for the type!");
        }
        return (T) tmp;
    }

    public static <T extends Number> T div(T t1, T t2) throws Exception {
        Object tmp = null;
        if (t1 instanceof Long && t2 instanceof Long) {
            tmp = (Long) t1 / (Long) t2;
        } else if (t1 instanceof Integer && t2 instanceof Integer) {
            tmp = (Integer) t1 / (Integer) t2;
        } else {
            throw new Exception("Cant use sum for the type!");
        }
        return (T) tmp;
    }

    /**
     * generic mod
     *
     * @param t1
     * @param t2
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T extends Number> T mod(T t1, T t2) throws Exception {
        Object tmp = null;
        if (t1 instanceof Long && t2 instanceof Long) {
            tmp = (Long) t1 % (Long) t2;
        } else if (t1 instanceof Integer && t2 instanceof Integer) {
            tmp = (Integer) t1 % (Integer) t2;
        } else {
            throw new Exception("Cant use mod for the type!");
        }
        return (T) tmp;
    }

    /**
     * get a generic number
     *
     * @param cls
     * @param i
     * @param <T>
     * @return
     */
    public static <T extends Number> T getNumber(Class<T> cls, int i) {
        Constructor<?> cons[] = cls.getConstructors();
        T res = null;
        try {
            res = (T) cons[0].newInstance(i);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * long to int
     *
     * @param lnum long number
     * @return
     */
    public static int long2int(long lnum) throws Exception {
        if (lnum > Integer.MAX_VALUE || lnum < Integer.MIN_VALUE) {
            throw new Exception();
        }
        return Integer.parseInt(String.valueOf(lnum));
    }

    /**
     * check number type
     *
     * @param o object type
     * @return is instance of number type
     */
    public <T> boolean checkIsNumberType(T o) {
        if (o instanceof Integer || o instanceof Long || o instanceof Short || o instanceof Byte || o instanceof Float || o instanceof Double) {
            return true;
        }
        return false;
    }
}
