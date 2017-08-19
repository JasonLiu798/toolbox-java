package com.atjl.util.character;

import com.atjl.util.collection.CollectionUtil;

import java.util.*;

public final class StringCheckUtil {
    private StringCheckUtil() {
        throw new UnsupportedOperationException();
    }

    private static final String NULL = "NULL";

    public static boolean isEmpty(String target) {
        return (target == null || target.length() == 0);
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
     * string array contain target string
     *
     * @param strArr
     * @param target
     * @return
     */
    public static boolean contains(String[] strArr, String target) {
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


    public static boolean strInStrings(String s, String... strings) {
        if (CollectionUtil.isEmpty(strings)) {
            return false;
        }

        List<String> l = Arrays.asList(strings);
        return strInCollection(s, l);
    }

    public static boolean strInCollection(String s, Collection<String> c) {
        if (StringCheckUtil.isEmpty(s)) {
            return false;
        }
        for (String str : c) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * exist one retur true
     *
     * @param raw
     * @param arr
     * @return
     */
    public static boolean batchContain(String raw, String[] arr) {
        if (CollectionUtil.isEmpty(arr) || StringCheckUtil.isEmpty(raw)) {
            return false;
        }
        for (String s : arr) {
            if (raw.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
