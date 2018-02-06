package com.atjl.util.character;

import com.atjl.util.collection.CollectionUtil;

import java.util.*;

public final class StringCheckUtil {
    private StringCheckUtil() {
        throw new UnsupportedOperationException();
    }

    private static final String NULL = "NULL";

    /**
     * ################## empty check ##################
     */
    public static boolean isEmpty(String target) {
        return (target == null || target.length() == 0);
    }

    public static boolean isEmptyEx(String target) {
        return (target == null || target.length() == 0 || NULL.equalsIgnoreCase(target));
    }

    public static boolean isNotEmpty(String target) {
        return !isEmpty(target);
    }

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(Object target) {
        return !isEmpty(target);
    }

    public static boolean isEmptyTrim(String raw) {
        if (raw == null) {
            return true;
        }
        raw = raw.trim();
        return raw.length() == 0;
    }

    public static boolean isNULL(String str) {
        return str == null || str.equalsIgnoreCase(NULL)
                || str.equalsIgnoreCase("");
    }

    /**
     * check Exist one empty
     *
     * @param targets string array
     * @return
     */
    public static boolean isExistEmpty(String... targets) {
        if (targets == null || targets.length == 0) {
            return true;
        }
        for (String str : targets) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExistEmpty(Object... targets) {
        if (targets == null || targets.length == 0) {
            return true;
        }
        for (Object str : targets) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check Exist one not empty
     *
     * @param targets
     * @return
     */
    public static boolean isExistNotEmpty(String... targets) {
        return !isAllEmpty(targets);
    }

    /**
     * check All is empty
     *
     * @param targets
     * @return
     */
    public static boolean isAllEmpty(String... targets) {
        if (targets == null || targets.length == 0) {
            return true;
        }
        for (String str : targets) {
            if (isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isAllEmpty(Object... targets) {
        if (targets == null || targets.length == 0) {
            return true;
        }
        for (Object str : targets) {
            if (isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check All is not empty
     *
     * @param targets
     * @return
     */
    public static boolean isAllNotEmpty(String... targets) {
        return !isExistEmpty(targets);
    }

    public static boolean isAllNotEmpty(Object... targets) {
        return !isExistEmpty(targets);
    }

    /**
     * ################## equal check ##################
     */
    /**
     * check string equals
     *
     * @param s1 string 1
     * @param s2 string 2
     * @return
     */
    public static boolean equal(String s1, String s2) {
        // all null
        if (s1 == null && s2 == null) {
            return true;
        }
        //one is null
        if (s1 == null || s2 == null) {
            return false;
        }
        //all not null
        return s1.equals(s2);
    }

    /**
     * exist one string equal,if src and tgt all null,return true
     *
     * @param src
     * @param tgt
     * @return
     */
    public static boolean equalExist(String src, String... tgt) {
        //all null
        if (src == null && CollectionUtil.isEmpty(tgt)) {
            return true;
        }
        if (!CollectionUtil.isEmpty(tgt)) {
            for (String t : tgt) {
                if (equal(src, t)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * ################## length check ##################
     */
    /**
     * length >0 ?
     *
     * @param str charSeq
     * @return is reach length
     */
    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    /**
     * length >0 ?
     *
     * @param str string
     * @return is reach length
     */
    public static boolean hasLength(String str) {
        return hasLength(((CharSequence) (str)));
    }


    /**
     * @param str
     * @return
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str))
            return false;
        int strLen = str.length();
        for (int i = 0; i < strLen; i++)
            if (!Character.isWhitespace(str.charAt(i)))
                return true;
        return false;
    }

    public static boolean hasText(String str) {
        return hasText(((CharSequence) (str)));
    }


    public static boolean isPisubstr(String qstr, String key) {
        boolean flag = false;
        if (null == qstr || "".equals(qstr.trim())) {
            return flag;
        }
        String[] qstrarr = qstr.split("&#04");
        String[] temparr = null;
        if (null != qstrarr && qstrarr.length > 0 && null != key
                && !"".equals(key.trim())) {
            int i = 0, lenI = qstrarr.length;
            for (; i < lenI; i++) {
                temparr = qstrarr[i].split("=");
                if (null != temparr && temparr.length > 0) {
                    if (key.equals(temparr[0])) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * ################## contain check ##################
     */
    /**
     * string array contain target string
     */
    public static boolean existContain(String[] strArr, String target) {
        if (null == strArr || null == target) {
            return false;
        }
        for (String temp : strArr) {
            if (temp.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contain(String raw, String tgt) {
        if (tgt==null|| raw==null) {
            return false;
        }
        if(raw.length() < tgt.length()){
            return false;
        }
        return raw.contains(tgt);
    }

    public static boolean containExist(String raw, String... arr) {
        if (CollectionUtil.isEmpty(arr)) {
            return false;
        }
        List<String> l = Arrays.asList(arr);
        return containExistInCollection(raw, l);
    }

    /**
     * raw是否包含了 arr内任何一个子字符串
     */
    public static boolean containExistInCollection(String raw, Collection<String> arr) {
        if (CollectionUtil.isEmpty(arr) || StringCheckUtil.isEmpty(raw)) {
            return false;
        }
        for (String s : arr) {
            if (!StringCheckUtil.isEmpty(s)) {
                if (raw.length() < s.length()) {
                    continue;
                }
                if (raw.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean startWith(String raw, String tgt) {
        if (StringCheckUtil.isEmpty(raw)) {
            return false;
        }
        if (StringCheckUtil.isEmpty(tgt)) {
            return false;
        }
        return raw.startsWith(tgt);
    }

    public static boolean startWithExist(String raw, String... arr) {
        if (CollectionUtil.isEmpty(arr)) {
            return false;
        }
        if (StringCheckUtil.isEmpty(raw)) {
            return false;
        }
        for (String str : arr) {
            if (!StringCheckUtil.isEmpty(str)) {
                if (raw.length() < str.length()) {
                    continue;
                }
                if (raw.startsWith(str)) {
                    return true;

                }
            }
        }
        return false;
    }
}
