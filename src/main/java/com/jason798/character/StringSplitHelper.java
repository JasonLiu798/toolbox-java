package com.jason798.character;

import java.util.HashMap;
import java.util.Map;

public final class StringSplitHelper {

    private StringSplitHelper() {
    }

    public static String[] split(String toSplit, String delimiter) {
        if (delimiter.length() != 1)
            throw new IllegalArgumentException("Delimiter can only be one character in length");
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

    public static Map splitEachArrayElementAndCreateMap(String array[], String delimiter, String removeCharacters) {
        if (array == null || array.length == 0)
            return null;
        Map map = new HashMap();
        for (int i = 0; i < array.length; i++) {
            String postRemove;
            if (removeCharacters == null)
                postRemove = array[i];
            else
                postRemove = StringHelper.replace(array[i], removeCharacters, "");
            String splitThisArrayElement[] = split(postRemove, delimiter);
            if (splitThisArrayElement != null)
                map.put(splitThisArrayElement[0].trim(), splitThisArrayElement[1].trim());
        }

        return map;
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

