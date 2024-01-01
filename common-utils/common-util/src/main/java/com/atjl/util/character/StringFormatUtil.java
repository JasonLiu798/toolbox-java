package com.atjl.util.character;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public final class StringFormatUtil {

    private static final String NULL = "NULL";
    private static final int NULLNUMSTR = -1;
    private static final String UTF8 = "UTF-8";
    private static final String FOLDER_SEPARATOR = "/";
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";
    private static final char EXTENSION_SEPARATOR = 46;
    public static final String DOT_SEP = ".";
    public static final String DOT_SEP_NO_REX = "\\.";

    public static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    /**
     * 首字母转小写
     */
    public static String toLowerCaseFirstOne(String s) {
        if (StringCheckUtil.isEmpty(s)) {
            return s;
        }
        //length at leatest one
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s) {
        if (StringCheckUtil.isEmpty(s)) {
            return s;
        }
        //length at leatest one
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * replace Oracle like special character
     *
     * @param str
     * @return
     */
    public static String replaceSpecialLikeCharacter(String str) {
        if (null == str) {
            return null;
        }
        str = str.replaceAll("%", "/%");
        str = str.replaceAll("_", "\\\\_");
        str = str.replaceAll("'", "\\\\'");
        return str;
    }

    public static String replaceBlank(String str) {
        if (StringCheckUtil.isEmpty(str)) {
            return str;
        }
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    public static String addDimmer(String target, String split, String dimmer) {
        if (target == null || target.length() == 0) {
            return "";
        }
        String[] s = target.split(split);
        StringBuilder sb = new StringBuilder("");
        for (String st : s) {
            sb.append(dimmer);
            sb.append(st);
            sb.append(dimmer);
            sb.append(split);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String formatFentoYuan(Long fen) {
        Long abFen = Math.abs(fen);
        String str = abFen.toString();
        if (abFen < 10) {
            str = "00" + str;
        } else if (abFen < 100) {
            str = "0" + str;
        }
        return (fen < 0 ? "-" : "") + str.substring(0, str.length() - 2) + "." + str.substring(str.length() - 2);
    }


    /**
     * format collection tring
     *
     * @param sep separator
     * @return
     */
    public static String concat(Collection<String> collection, String sep) {
        if (null == collection || collection.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        for (String str : collection) {
            sb.append(str).append(sep);
        }

        return sb.substring(0, sb.lastIndexOf(sep));
    }


    public static String deleteSpace(String str) {
        return str.replaceAll("\\s", StringUtils.EMPTY);
    }

    /**
     * 重量舍入。<br/>
     * 小于等于1kg的为1kg,1.1 ~ 1.5kg为1.5kg,1.6 ~ 2kg为2kg,2.1 ~ 2.5kg 为2.5kg。 以此类推。
     *
     * @param weight
     */
    public static String rangeWeight(String weight) {

        if (StringUtils.isBlank(weight)) {
            return weight;
        }

        String rangeWeight = StringUtils.EMPTY;

        BigDecimal decimalWeight = null;
        try {
            decimalWeight = new BigDecimal(weight);
        } catch (Exception e) {
            log.error("处理重量失败，非法的重量参数：" + weight, e);
            return weight;
        }

        double doubleWeight = decimalWeight.doubleValue();

        double startWeight = 1.0d;

        BigDecimal step = new BigDecimal(0.5);

        if (decimalWeight.doubleValue() < startWeight) {
            rangeWeight = String.valueOf(startWeight);
        } else {
            if (doubleWeight == Math.ceil(doubleWeight)) {
                rangeWeight = weight;
            } else if (Math.ceil(doubleWeight) < decimalWeight.add(step).doubleValue()) {
                rangeWeight = String.valueOf(Math.ceil(decimalWeight.doubleValue()));
            } else {
                rangeWeight = new BigDecimal(Math.floor(doubleWeight)).add(step).toString();
            }
        }

        return rangeWeight;
    }

    /**
     * 左补字符串
     *
     * @param origin 原字符串
     * @param length 补位后的字符串长度
     * @param addStr 补位的字符串
     * @return
     */
    public static String lpad(String origin, int length, Character addStr) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isBlank(origin)) {
            for (int i = 0; i < length; i++) {
                sb.append(addStr);
            }
            return sb.toString();
        }
        if (origin.length() > length) {
            return origin;
        }
        for (int i = 0; i < length - origin.length(); i++) {
            sb.append(addStr);
        }
        sb.append(origin);
        return sb.toString();
    }

    /**
     * replace special character
     *
     * @param input
     * @return
     */
    public static String replaceSpecialCharacter(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            switch (c) {
                // case '\"':
                // sb.append("\\\"");
                // break;
                case '\\': // 如果不处理单引号，可以释放此段代码，若结合下面的方法处理单引号就必须注释掉该段代码
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b': // 退格
                    sb.append("\\b");
                    break;
                case '\f': // 走纸换页
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");// 换行
                    break;
                case '\r': // 回车
                    sb.append("\\r");
                    break;
                case '\t': // 横向跳格
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String escapeSpecialCharacter(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            switch (c) {
                // case '\"':
                // sb.append("\\\"");
                // break;
                // case '\\': // 如果不处理单引号，可以释放此段代码，若结合下面的方法处理单引号就必须注释掉该段代码
                // sb.append("\\\\");
                // break;
                // case '/':
                // sb.append("\\/");
                // break;
                case '\b': // 退格
                    sb.append("");
                    break;
                case '\f': // 走纸换页
                    sb.append("");
                    break;
                case '\n':
                    sb.append("");// 换行
                    break;
                case '\r': // 回车
                    sb.append("");
                    break;
                // case '\t': // 横向跳格
                // sb.append("\\t");
                // break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * trim String
     *
     * @param str string to trim
     * @return string trimed
     */
    public static String trim(String str) {
        if (StringCheckUtil.isEmpty(str)) {
            return str;
        }
        return str.trim();
    }


    /**
     * null => null
     * ""   =>  ""
     * "\"" =>  ""
     * "\"\"" => ""
     * "\"aaa\"" => aaa
     * "\"aa" => aa
     * "aa\"" => aa
     *
     * @param str string to trim
     * @return trimed string
     */
    public static String trimQuote(String str) {
        if (StringCheckUtil.isEmpty(str)) {
            return str; //nothing to trim quote
        }
        str = str.trim();
        if (StringCheckUtil.isEmpty(str)) {
            return str;//nothing to trim quote
        }
        int len = str.length();
        int left = -1;
        if (str.charAt(0) == '"') {
            left = 1;
        }
        int right = -1;
        if (str.charAt(len - 1) == '"') {
            right = len - 1;
        }
        //left not found,right not found
        if (right < 0 && left < 0) {
            return str;
        }
        //situation "\"" ; "\"\""
        if ((left == right) || (left == right + 1) && left == 1) {
            return "";
        }
        if (left < 0) {
            left = 0;
        }
        if (right < 0) {
            right = len;
        }

        str = str.substring(left, right);
        str = str.trim();
        return str;
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
        StringBuilder sb = new StringBuilder();
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
     * @return string to array by separator
     */
    public static int[] stringToArray(String ids, String separator) {
        String[] t = ids.split(separator);
        int[] arrays = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            arrays[i] = Integer.parseInt(t[i]);
        }
        return arrays;
    }

    /**
     * unicode 转中文
     *
     * @param dataStr {@link String} 目标字符串
     * @return transfer dataStr to chinese
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
            buffer.append(new Character(letter));
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 中文 转 unicode
     *
     * @param dataStr {@link String} 目标字符串
     * @return unicode String
     */
    public static String encodeUnicode(final String dataStr) {
        StringBuilder retString = new StringBuilder();
        for (int i = 0; i < dataStr.length(); i++) {
            retString.append("\\u" + Integer.toHexString(dataStr.charAt(i) & 0xffff));
        }
        return retString.toString();
    }


    /**
     * add 0 in front of string until length equal vOutputLen
     *
     * @param vSourceString add zero in front until length reach vOutputLen
     * @param vOutputLen    max length
     * @return add 0 string
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
     * enumeration to string array
     *
     * @param enumeration
     * @return
     */
    public static String[] toStringArray(Enumeration<?> enumeration) {
        if (enumeration == null) {
            return null;
        } else {
            List<?> list = (List<?>) Collections.list(enumeration);
            return (String[]) list.toArray(new String[list.size()]);
        }
    }


    public static String replace(String inString, String oldPattern,
                                 String newPattern) {
        if (!StringCheckUtil.hasLength(inString) || !StringCheckUtil.hasLength(oldPattern)
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
        if (StringCheckUtil.isPisubstr(qstr, key)) {
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
                && !"".equals(key.trim()) && StringCheckUtil.isPisubstr(qstr, key.trim())) {
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

    /**
     * convert an object array to a string
     *
     * @param array the object array
     * @return the result string
     */
    public static String arrayToString(Object array[]) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < array.length; i++) {
            if (result.length() > 0) {
                result.append(",");
            }
            result.append(array[i].toString());
        }
        result.append("]");
        return result.toString();
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
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length - raw.length(); i++) {
            result.append(complement);
        }
        result.append(raw);
        return result.toString();
    }

    public static String sufComplement(String raw, int length, char complement) {
        StringBuilder result = new StringBuilder(raw);
        for (int i = 0; i < length - raw.length(); i++) {
            result.append(complement);
        }
        return result.toString();
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


    public final static String encode(String info) {
        return encode(info, UTF8);
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
            return URLDecoder.decode(info, UTF8);
        } catch (Exception e) {
            return info;
        }
    }


    public final static String encodeStr(String str) {
        StringTokenizer st = new StringTokenizer(str, " ");
        int count = st.countTokens();
        StringBuilder message = new StringBuilder();
        String[] tokens = new String[count];
        for (int i = 0; i < count; i++) {
            try {
                tokens[i] = URLEncoder.encode(st.nextToken(), UTF8) + " ";
            } catch (UnsupportedEncodingException e) {
                return tokens[i];
            }
        }
        for (int i = 0; i < tokens.length; i++) {
            message.append(tokens[i]);
        }
        return message.toString();
    }


    public final static String getNullString(String str) {
        if (StringCheckUtil.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public final static String getIVRNullString(String str) {
        if (StringCheckUtil.isNULL(str)) {
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

    /**
     * clear string buffer
     *
     * @param buf string buffer
     */
    public static void clean(StringBuffer buf) {
        if (buf != null) {
            buf.delete(0, buf.length());
        }
    }

    public static String integerToZhCn(int i) {
        StringBuilder builder = new StringBuilder();
        return builder.toString();
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


    public final static String formatDate(Date date, SimpleDateFormat sdf) {
        if (date == null) {
            return NULL;
        } else {
            return sdf.format(date);
        }
    }


    /**
     * 过滤 外边双引号
     * null => null
     * ""   =>  空字符串
     * "aaa" => aaa
     *
     * @param string string to filter
     * @return filtered string
     */
    public static String filterOuterQuote(String string) {
        if (StringCheckUtil.isEmpty(string) || string.length() < 2) {
            return string;
        }
        if (string.length() == 2) {
            return "";
        }
        int len = string.length();
        string = string.substring(1, len - 1);
        return string;
    }

    public static String rmHtml(String raw) {
        if (raw == null) {
            return raw;
        }
        int left = raw.indexOf("<");
        int right = raw.indexOf(">");
        while (left >= 0 && left < right && raw.length() > 0) {
            raw = raw.substring(0, left) + raw.substring(right + 1);
            left = raw.indexOf("<");
            right = raw.indexOf(">");
        }
        return raw;
    }

    /**
     * rm <xxxx> of string
     *
     * @param raw
     * @param n
     * @return
     */
    public static String rmHtml(String raw, int n) {
        if (n <= 0) {
            return raw;
        }
        for (int i = 0; i < n; i++) {
            raw = rmHtmlInner(raw, "<", ">");
        }
        return raw;
    }

    public static String rmHtmlInner(String raw, String left, String right) {
        if (StringCheckUtil.isEmpty(raw)) {
            return raw;
        }
        int idxLeft = raw.indexOf(left);
        int idxRight = raw.indexOf(right);
        if (idxLeft < 0 || idxRight < 0 || idxRight < idxLeft || idxLeft > raw.length() || idxRight > raw.length()) {
            return raw;
        }
        raw = raw.substring(0, idxLeft) + raw.substring(idxRight + 1);
        return raw;
    }
}
