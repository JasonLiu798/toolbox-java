package com.atjl.util.character;

import com.atjl.util.common.CheckUtil;

import java.util.*;

public final class StringSplitUtil {
    private StringSplitUtil() {
        throw new UnsupportedOperationException();
    }

    private static final String DFT_SEP = ",";

    /**
     * split string to array by delimiers
     *
     * @param str
     * @param delimiters
     * @return
     */
    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * split
     *
     * @param str
     * @param delimiters
     * @param trimTokens
     * @param ignoreEmptyTokens
     * @return
     */
    public static String[] tokenizeToStringArray(String str, String delimiters,
                                                 boolean trimTokens, boolean ignoreEmptyTokens) {
        if (str == null)
            return null;
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List tokens = new ArrayList();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens)
                token = token.trim();
            if (!ignoreEmptyTokens || token.length() > 0)
                tokens.add(token);
        }
        return toStringArray(tokens);
    }


    /**
     * enumeration to string array
     *
     * @param enumeration
     * @return
     */
    public static String[] toStringArray(Enumeration enumeration) {
        if (enumeration == null) {
            return null;
        } else {
            List list = (List) Collections.list(enumeration);
            return (String[]) list.toArray(new String[list.size()]);
        }
    }

    /**
     * split string by delimiter,arr.length = size
     *
     * @param str       string
     * @param delimiter sep
     * @param size      size
     * @return arr
     */
    public static String[] splitCheckSize(String str, String delimiter, int size) {
        CheckUtil.checkStrExistNull(str, delimiter);
        if (size <= 0) {
            throw new IllegalArgumentException("size must > 0");
        }
        String[] arr = str.split(delimiter);
        if (arr.length != size) {
            throw new IllegalStateException("split result length not equal size");
        }
        return arr;
    }

    /**
     * 字符串分离
     *
     * @param toSplit
     * @param delimiter
     * @return
     */
    public static String[] split(String toSplit, String delimiter) {
        if (StringCheckUtil.isEmpty(delimiter)) {
            return new String[]{};
        }
        return toSplit.split(delimiter);
    }

    /**
     * 按单个字符 分离，性能优化版
     *
     * @param toSplit
     * @param delimiter
     * @return
     */
    public static String[] splitEx(String toSplit, String delimiter) {
        if (StringCheckUtil.isEmpty(delimiter)) {
            return new String[]{toSplit};
        }
        if (StringCheckUtil.isEmpty(toSplit)) {
            return new String[]{};
        }
        if (toSplit.length() < delimiter.length()) {
            return new String[]{toSplit};
        }
        String tmp = toSplit;
        while (tmp.length() > 0) {

        }

        return toSplit.split(delimiter);
    }

    /**
     * split two optimize
     *
     * @param toSplit   string to split
     * @param delimiter split delimiter
     * @return splited String[]
     */
    public static String[] splitToTwo(String toSplit, String delimiter) {
        if (delimiter.length() != 1) {
            return new String[]{};
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return new String[]{};
        } else {
            String beforeDelimiter = toSplit.substring(0, offset);
            String afterDelimiter = toSplit.substring(offset + 1);
            return (new String[]{
                    beforeDelimiter, afterDelimiter
            });
        }
    }

    /**
     * @param array
     * @param delimiter
     * @param removeCharacters
     * @return
     */
    public static Map splitEachArrayElementAndCreateMap(String array[], String delimiter, String removeCharacters) {
        if (array == null || array.length == 0)
            return null;
        Map map = new HashMap();
        for (int i = 0; i < array.length; i++) {
            String postRemove;
            if (removeCharacters == null)
                postRemove = array[i];
            else
                postRemove = StringUtilEx.replace(array[i], removeCharacters, "");
            String splitThisArrayElement[] = splitToTwo(postRemove, delimiter);
            if (splitThisArrayElement != null)
                map.put(splitThisArrayElement[0].trim(), splitThisArrayElement[1].trim());
        }
        return map;
    }


    /**
     * @param str to split
     * @return
     */
    public static String[] tokenizeToStringArray(String str) {
        return tokenizeToStringArray(str, DFT_SEP);
    }


    /**
     * collection to string array
     *
     * @param collection collection
     * @return String[]
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null)
            return null;
        else
            return (String[]) collection.toArray(new String[collection.size()]);
    }


    public static String substringBeforeLast(String str, String separator) {
        if (str == null || separator == null || str.length() == 0 || separator.length() == 0)
            return str;
        int pos = str.lastIndexOf(separator);
        if (pos == -1)
            return str;
        else
            return str.substring(0, pos);
    }

    public static String substringAfterLast(String str, String separator) {
        if (str == null || str.length() == 0)
            return str;
        if (separator == null || separator.length() == 0)
            return "";
        int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == str.length() - separator.length())
            return "";
        else
            return str.substring(pos + separator.length());
    }


    /**
     * s='/a/b/c'
     * delimiter='/'
     * ==> res='/a/b'
     *
     * @param str       string to remove
     * @param delimiter separater
     * @return
     */
    public static String removeAfterLastDelimiter(String str, String delimiter) {
        if (StringCheckUtil.isExistEmpty(str, delimiter)) {
            return str;
        }
        int index = str.lastIndexOf(delimiter);
        if (index < 0 || index > str.length()) {
            return str;
        }
        return str.substring(0, index);
    }

}

