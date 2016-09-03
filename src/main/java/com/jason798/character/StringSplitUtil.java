package com.jason798.character;

import java.util.*;

public final class StringSplitUtil {

    private static final String DFT_SEP = ",";
    private StringSplitUtil() {
    }

    /**
     * split two optimize
     * @param toSplit string to split
     * @param delimiter split delimiter
     * @return splited String[]
     */
    public static String[] splitTwo(String toSplit, String delimiter) {
        if( StringUtil.isEmpty(toSplit) || StringUtil.isEmpty(delimiter) ){
            throw new IllegalArgumentException("argument can't be null");
        }
        if (  delimiter.length() != 1 ) {
            throw new IllegalArgumentException("Delimiter can only be one character in length");
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return null;
        } else {
            String beforeDelimiter = toSplit.substring(0, offset);
            String afterDelimiter = toSplit.substring(offset + 1);
            return (new String[]{
                    beforeDelimiter, afterDelimiter
            });
        }
    }

    /**
     *
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
                postRemove = StringUtil.replace(array[i], removeCharacters, "");
            String splitThisArrayElement[] = splitTwo(postRemove, delimiter);
            if (splitThisArrayElement != null)
                map.put(splitThisArrayElement[0].trim(), splitThisArrayElement[1].trim());
        }
        return map;
    }


    /**
     *
     * @param str to split
     * @return
     */
    public static String[] tokenizeToStringArray(String str) {
        return tokenizeToStringArray(str,DFT_SEP);
    }

    /**
     *
     * @param str
     * @param delimiters
     * @return
     */
    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * split string by delimiter,
     *
     * @param str string to split
     * @param delimiters sep
     * @param trimTokens
     * @param ignoreEmptyTokens
     * @return
     */
    public static String[] tokenizeToStringArray(String str, String delimiters,
                                                 boolean trimTokens, boolean ignoreEmptyTokens) {
        if (str == null)
            return null;
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<>();
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
}

