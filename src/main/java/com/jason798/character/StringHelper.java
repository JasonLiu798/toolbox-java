package com.jason798.character;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String associate functions
 */
public final class StringHelper {

    private static final String NULL = "NULL";
    private static final int NULLNUMSTR = -1;
    private static final String code = "UTF-8";
    private static final String FOLDER_SEPARATOR = "/";
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";
    private static final char EXTENSION_SEPARATOR = 46;
	public static final String DOT_SEP = ".";
	public static final String DOT_SEP_NO_REX = "\\.";


    /**
     * 判空
     *
     * @param str {@link Object} 对象或字符串
     * @return {@link boolean}
     */
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    /**
     * 去除字符串前后空格
     *
     * @param str {@link String} 待去除空格的字符串
     * @return {@link String} 去除空格后的字符串
     */
    public static String trim(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.trim();
    }

    /**
     * Replace all occurences of a substring within a string with another
     * string.
     *
     * @param inString   String to examine
     * @param oldPattern String to replace
     * @param newPattern String to insert
     * @return a String with the replacements
     */
    public static String replaceAll(String inString, String oldPattern, String newPattern) {
        if (null == inString || oldPattern == null || newPattern == null) {
            return inString;
        }
        String rlt = inString;
        StringBuffer sb = new StringBuffer();
        while (true) {
            int idx = rlt.indexOf(oldPattern);
            if (idx < 0)
                break;
            sb.delete(0, sb.length());
            if (idx > 0) {
                sb.append(rlt.substring(0, idx));
            }
            sb.append(newPattern);
            sb.append(rlt.substring(idx + oldPattern.length()));
            rlt = sb.toString();
        }
        return rlt;
    }

    /**
     * 获取唯一的标识值
     *
     * @return {@link String}
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 根据用","隔开的字符Id转换成list
     *
     * @param id {@link String}
     * @return {@link List}
     */
    public static List<Integer> getListId(String id) {
        String str[] = id.split(",");
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < str.length; i++) {
            list.add(Integer.parseInt(str[i]));
        }
        return list;
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param arg1 {@link String}
     * @param arg2 {@link String}
     * @return {@link Boolean}
     */
    public static Boolean isEqualString(String arg1, String arg2) {
        return arg1.equals(arg2);
    }

    /**
     * 格式化字符串
     *
     * @param arg     {@link String}
     * @param objects {@link Object}
     * @return {@link String}
     */
    public static String formatterString(String arg, Object... objects) {
        return MessageFormat.format(arg, objects);
    }

    /**
     * String字符串转整形数组
     *
     * @param ids       {@link String} 字符串
     * @param separator {@link String} 符号
     * @return
     */
    public static int[] StringToArray(String ids, String separator) {
        String[] t = ids.split(separator);
        int[] arrays = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            arrays[i] = Integer.parseInt(t[i]);
        }
        return arrays;
    }

    /**
     * uncode 转中文
     *
     * @param dataStr {@link String} 目标字符串
     * @return
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 中文 转 uncode
     *
     * @param dataStr {@link String} 目标字符串
     * @return
     */
    public static String encodeUnicode(final String dataStr) {
        String retString = "";
        for (int i = 0; i < dataStr.length(); i++) {
            retString += "\\u" + Integer.toHexString(dataStr.charAt(i) & 0xffff);
        }
        return retString;
    }

    /**
     * 查询数字是否存在数组中
     *
     * @param array  目标数组
     * @param number 目标数字
     * @return
     */
    public static boolean numberInArray(int[] array, int number) {
        int start = 0, end, middle, count = 0;
        int N = array.length;
        end = N;
        middle = (start + end) / 2;
        while (number != array[middle]) {
            if (number > array[middle])
                start = middle;
            else if (number < array[middle])
                end = middle;
            middle = (start + end) / 2;
            count++;
            if (count > N / 2)
                break;
        }
        if (count > N / 2)
            return false;
        else
            return true;
    }

    /**
     * add 0 in front of string until length equal vOutputLen
     *
     * @param vSourceString
     * @param vOutputLen
     * @return
     */
    public static String addZeroFront(String vSourceString, int vOutputLen) {
        String strNewString;
        strNewString = vSourceString;
        for (int i = 0; i < vOutputLen - vSourceString.length(); i++) {
            strNewString = "0" + strNewString;
        }
        return strNewString;
    }

    /**
     * length >0 ?
     *
     * @param str
     * @return
     */
    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    /**
     * length >0 ?
     *
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return hasLength(((CharSequence) (str)));
    }

    /**
     * capture name
     *
     * @return
     */
    public static String upperFirstLetter(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }


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
     * collection to string array
     *
     * @param collection
     * @return
     */
    public static String[] toStringArray(Collection collection) {
        if (collection == null)
            return null;
        else
            return (String[]) collection.toArray(new String[collection.size()]);
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

    public static String replace(String inString, String oldPattern,
                                 String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern)
                || newPattern == null)
            return inString;
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        int index = inString.indexOf(oldPattern);
        int patLen = oldPattern.length();
        for (; index >= 0; index = inString.indexOf(oldPattern, pos)) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
        }
        sb.append(inString.substring(pos));
        return sb.toString();
    }

    /**
     * split to map
     *
     * @param content
     * @param sign
     * @return
     */
    public static Map<String, String> getContentBySep(String content, String sign) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (null == content || "".equals(content.trim())) {
                return map;
            }
            if (null == sign || "".equals(sign.trim())) {
                return map;
            }
            String[] dataArr = content.split(sign);
            if (null != dataArr && dataArr.length > 0) {
                int init = 0, max = dataArr.length;
                String[] temparr = null;
                for (; init < max; init++) {
                    temparr = dataArr[init].split("=");
                    map.put(null == temparr[0] ? "" : temparr[0],
                            null == temparr[1] ? "" : temparr[1]);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return map;
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


    public static String getValByKey(String qstr, String key, String sign) {
        String result = "";
        String[] qstrarr = qstr.split(sign);
        String[] temparr = null;
        if (null != qstrarr && qstrarr.length > 0 && null != key
                && !"".equals(key)) {
            int i = 0, lenI = qstrarr.length;
            for (; i < lenI; i++) {
                temparr = qstrarr[i].split("=");
                if (null != temparr && temparr.length > 0) {
                    if (key.equals(temparr[0])) {
                        result = temparr[1];
                        break;
                    }
                }
            }
        }
        return result;
    }


    public static String setValByKey(String qstr, String key, String val,
                                     String sign) {
        String result = "";
        if (isPisubstr(qstr, key)) {
            String[] qstrarr = qstr.split(sign);
            String[] temparr = null;
            int idxS = 0;
            int idxE = 0;
            if (null != qstrarr && qstrarr.length > 0 && null != key
                    && !"".equals(key) && null != val) {
                int i = 0, lenI = qstrarr.length;
                for (; i < lenI; i++) {
                    temparr = qstrarr[i].split("=");
                    if (key.equals(temparr[0])) {
                        idxS = qstr.indexOf(key + "=" + temparr[1])
                                + key.length() + 1;
                        idxE = qstr.indexOf(key + "=" + temparr[1])
                                + (key.length() + 1) + temparr[1].length();
                        break;
                    }
                }
                result = qstr.substring(0, idxS) + val + qstr.substring(idxE);
            }
        } else {
            if (null == qstr || "".equals(qstr.trim())) {
                result = key + "=" + val;
            } else {
                result = qstr + sign + key + "=" + val;
            }
        }
        return result;
    }


    public static String delStrKey(String qstr, String key, String sign) {
        String result = "";
        String[] qstrarr = qstr.split(sign);
        String[] temparr = null;
        if (null != qstrarr && qstrarr.length > 0 && null != key
                && !"".equals(key.trim()) && isPisubstr(qstr, key.trim())) {
            int i = 0, lenI = qstrarr.length;
            for (; i < lenI; i++) {
                temparr = qstrarr[i].split("=");
                if (null != temparr && temparr.length > 0) {
                    if (key.equals(temparr[0])) {
                        if (0 == i) {
                            result = qstr.replace(temparr[0] + "=" + temparr[1]
                                    + sign, "");
                        } else if ((lenI - 1) == i) {
                            result = qstr.replace(sign + temparr[0] + "="
                                    + temparr[1], "");
                        } else {
                            result = qstr.replace(sign + temparr[0] + "="
                                    + temparr[1], "");
                        }
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static char firstChar(String str) {
        return str.charAt(0);
    }

    public static char lastChar(String str) {
        return str.charAt(str.length() - 1);
    }

    public static boolean isIvrNull(String str) {
        return str == null || str.equalsIgnoreCase(NULL)
                || str.equalsIgnoreCase("");
    }

    /**
     * convert an object array to a string
     *
     * @param array the object array
     * @return the result string
     */
    public static String arrayToString(Object array[]) {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            if (result.length() > 0) {
                result += ",";
            }
            result += array[i].toString();
        }
        return "[" + result + "]";
    }

    /**
     * complement a string to the length with the char
     *
     * @param raw
     * @param length
     * @param complement
     * @return
     */
    public static String preComplement(String raw, int length, char complement) {
        String result = "";
        for (int i = 0; i < length - raw.length(); i++) {
            result += complement;
        }
        return result + raw;
    }

    public static String sufComplement(String raw, int length, char complement) {
        String result = raw;
        for (int i = 0; i < length - raw.length(); i++) {
            result += complement;
        }
        return result;
    }

    /**
     * convert a string to bytes without any encod.
     *
     * @param string the string
     * @return the byte array
     */
    public static byte[] toBytesRaw(String string) {
        char[] chars = string.toString().toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        return bytes;
    }

    /**
     * convert the string as [0xFFFEDADB] to the bytes array : {0xFF, 0xFE,
     * 0xDA, 0xDB}
     *
     * @param hex the hex string
     * @return the bytes array
     */
    public static byte[] toBytesHex(String hex) {
        hex = hex.toUpperCase();
        /*
         * if (hex.startsWith("[")) { hex = hex.substring(1); } if
		 * (hex.endsWith("]")) { hex = hex.substring(0, hex.length() - 1); } if
		 * (hex.startsWith("0X")) { hex = hex.substring(2); }
		 */
        char[] chars = hex.toString().toCharArray();
        byte[] bytes = new byte[chars.length / 2];
        for (int i = 0; i < chars.length; i += 2) {
            bytes[i / 2] = (byte) Integer.parseInt(
                    "" + chars[i] + chars[i + 1], 16);
        }
        return bytes;
    }

    public static String integerToZhCn(int i) {
        StringBuilder builder = new StringBuilder();
        return builder.toString();
    }


    public final static String[] decode(String infos[]) {
        String decodeInfos[] = new String[infos.length];
        for (int i = 0; i < infos.length; i++) {
            try {
                decodeInfos[i] = URLDecoder.decode(infos[i], code);
            } catch (UnsupportedEncodingException e) {
                return infos;
            }
        }
        return decodeInfos;
    }

    public final static String decode(String info, String decodeType) {
        if (info == null)
            return null;
        try {
            return URLDecoder.decode(info, decodeType);
        } catch (Exception e) {
            return info;
        }
    }


    public final static String encode(String info, String decodeType) {
        try {
            if (info == null) {
                return NULL;
            } else {
                info = URLEncoder.encode(info, decodeType);
            }
        } catch (Exception e) {
            return info;
        }
        return info;
    }

    public final static String decode(String info) {
        if (info == null)
            return null;
        try {
            return URLDecoder.decode(info, code);
        } catch (Exception e) {
            return info;
        }
    }

    public final static String encode(String info) {
        try {
            if (info == null) {
                return NULL;
            } else {
                info = URLEncoder.encode(info, code);
            }
        } catch (Exception e) {
            return info;
        }
        return info;
    }


    public final static String encodeStr(String str) {
        StringTokenizer st = new StringTokenizer(str, " ");
        int count = st.countTokens();
        String message = "";
        String[] tokens = new String[count];
        for (int i = 0; i < count; i++) {
            try {
                tokens[i] = URLEncoder.encode(st.nextToken(), code) + " ";
            } catch (UnsupportedEncodingException e) {
                return tokens[i];
            }
        }
        for (int i = 0; i < tokens.length; i++) {
            message += tokens[i];
        }

        return message;
    }

    public final static String formatDate(Date date, SimpleDateFormat sf) {
        if (date == null) {
            return NULL;
        } else {
            return sf.format(date);
        }
    }


    public final static String getNullString(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str;
    }

    public final static String getIVRNullString(String str) {
        if (isIvrNull(str)) {
            return null;
        }
        return str;
    }

    public static String[] decode(String str, String dim, int num) {
        String s = null;
        String[] strs = new String[num];

        int position = 0;

        for (int i = 0; i < num; i++) {
            position = str.indexOf(dim);
            if (position >= 0) {
                s = str.substring(0, position);
                str = str.substring(position + 2, str.length());
            }
            if (i != num - 1)
                strs[i] = s;
            else
                strs[i] = str.substring(0, str.length());
        }
        return strs;
    }




    /**
     * make double value reserve until 'N' after the decimal point
     *
     * @param value
     * @param afterpoint eg. 31.233->32.23 afterpoint=3
     * @return
     */
    public static double cutDouble2point(double value, int afterpoint) {
        String temp = String.valueOf(value);
        int endIndex = temp.indexOf(".") + afterpoint;
        if (endIndex < temp.length()) {
            temp = temp.substring(0, endIndex);
            return Double.parseDouble(temp);
        } else {
            return value;
        }

    }

    public static void clean(StringBuffer buf) {
        if (buf != null) {
            buf.delete(0, buf.length());
        }
    }


}