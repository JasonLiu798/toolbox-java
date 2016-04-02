package com.jason.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String util
 *
 * @author JianLong
 * @date 2013/11/27 11:10
 */
public class JStringUtil {

    private static Logger log = LoggerFactory.getLogger(JStringUtil.class);
    private static Random randGen = new Random();
    private static final String nullStr = "NULL";
    private static final int nullNumStr = -1;
    private static final String code = "UTF-8";
    private static final String FOLDER_SEPARATOR = "/";
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";
    private static final char EXTENSION_SEPARATOR = 46;


    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasLength(String str) {
        return hasLength(((CharSequence) (str)));
    }

    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }


    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

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

    public static String[] toStringArray(Collection collection) {
        if (collection == null)
            return null;
        else
            return (String[]) collection.toArray(new String[collection.size()]);
    }

    public static String[] toStringArray(Enumeration enumeration) {
        if (enumeration == null) {
            return null;
        } else {
            List list = (List) Collections.list(enumeration);
            return (String[]) list.toArray(new String[list.size()]);
        }
    }

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


    static int byte2int(byte b) {
        if (b < 0) {

            return (int) b + 0x100;
        }

        return b;
    }


    public static final Long getRandomNumber() {
        return new Long(Math.abs(randGen.nextLong()));
    }

    static int GG(int a, int b, int c, int d, int x, int s, int ac) {
        a += (G(b, c, d) + x + ac);
        a = ROTATE_LEFT(a, s);

        return a + b;
    }

    static int G(int x, int y, int z) {
        return ((x & z) | (y & ~z));
    }

    static int ROTATE_LEFT(int x, int n) {
        return ((x << n) | (x >>> (32 - n)));
    }


    public static Map<String, String> getContentBySep(String content,
                                                      String sign) {
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


    public static boolean isDigit(String str) {
        boolean flag = true;
        if (null != str && !"".equals(str.trim())) {
            int init = 0, max = str.trim().length();
            for (; init < max; init++) {
                if (!Character.isDigit(str.trim().charAt(init))) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
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


    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0)
            return true;
        return false;
    }

    public static boolean isNull(String str) {
        return str == null || str.equalsIgnoreCase(nullStr);
    }

    public static boolean isIvrNull(String str) {
        return str == null || str.equalsIgnoreCase(nullStr)
                || str.equalsIgnoreCase("");
    }


    public static boolean isNumberNull(int number) {
        return nullNumStr == number;
    }


    public static boolean isNumberNull(long number) {
        return nullNumStr == number;
    }


    /**
     * to upper case a string's first charactor
     *
     * @param str the string
     * @return the string after convert
     */
    public static String upperCaseFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
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
                return nullStr;
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
                return nullStr;
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
            return nullStr;
        } else {
            return sf.format(date);
        }
    }


    public final static String getNullString(String str) {
        if (isNull(str)) {
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


    public static Date string2Date(String source) {//synchronized
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(source);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String date2String(Date date) {//synchronized
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String str = null;
        try {
            if (date == null) {
                return nullStr;
            }
            str = format.format(date);
        } catch (Exception e) {
        }
        return str;

    }


    public static String date2HHmmss(Date date) {
        SimpleDateFormat formatymd = new SimpleDateFormat(
                "HHmmss");
        String str = null;
        try {
            if (date == null) {
                return nullStr;
            }
            str = formatymd.format(date);
        } catch (Exception e) {
        }
        return str;

    }

    public static String date2yyyyMMdd(Date date) {
        SimpleDateFormat formatymd = new SimpleDateFormat(
                "yyyyMMdd");
        String str = null;
        try {
            if (date == null) {
                return nullStr;
            }
            str = formatymd.format(date);
        } catch (Exception e) {
        }
        return str;

    }


    public static String date2HHmm(Date date) {
        SimpleDateFormat formathm = new SimpleDateFormat(
                "HHmm");
        String str = null;
        try {
            if (date == null) {
                return nullStr;
            }
            str = formathm.format(date);
        } catch (Exception e) {
        }
        return str;

    }


    /**
     * is integer
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(str);
        return m.matches();
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


    public static String getNotEmpty(String str) {
        if (isNull(str) || isEmpty(str)) {
            return nullStr;
        }
        return str;
    }

    public static String encodeNotEmpty(String str) {
        if (isNull(str) || isEmpty(str)) {
            return nullStr;
        }
        return encode(str);
    }

    public final static Date strToDateTime(String year, String month, String day, String hour, String minute, String
            second, String timezone) {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat formatymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(day);
        sb.append(" ");
        sb.append(hour);
        sb.append(":");
        sb.append(minute);
        sb.append(":");
        sb.append(second);
//		formatymdhms.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            date = formatymdhms.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTimeZone(TimeZone.getTimeZone(timezone));
        calendar.setTime(date);
//		calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }


    public final static Date getGMT2East8TimeZone(String year, String month, String day, String hour, String minute,
                                                  String second, String timezone) {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat formatymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(day);
        sb.append(" ");
        sb.append(hour);
        sb.append(":");
        sb.append(minute);
        sb.append(":");
        sb.append(second);

//		formatymdhms.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            date = formatymdhms.parse(sb.toString());
        } catch (ParseException e) {
            log.debug("SimpleDateFormat�����쳣");
        }
        calendar.setTimeZone(TimeZone.getTimeZone(timezone));
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }

    public final static Date getGMT2East8TimeZone(String sDate, String sTime, String sTimezone) {
        SimpleDateFormat formatymdhms = new SimpleDateFormat("ddMMyyHHmmss");
        Date date = null;
        try {
            date = formatymdhms.parse(sDate + sTime);
        } catch (ParseException e) {
            log.debug("SimpleDateFormat");
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone(sTimezone));
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }

    public static String getDate2Str(String date) throws Exception {
        SimpleDateFormat formatymdhms = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatymdhms.format(date);
    }

    public static void main(String[] args) {
        System.out.println(JStringUtil.string2Date("2012-04-01 23:59:59").getTime());
        ;
    }


    /**
     * add zero
     *
     * @param speed
     * @return
     */
    public static String addZeroEnd(double speed) {
        String speedStr = String.valueOf(speed);
        int index = speedStr.indexOf(".");
        if (speedStr.length() - speedStr.substring(0, index).length() < 4) {
            speedStr = (speedStr + "0000");
        }
        String temp = speedStr.substring(0, index) + "."
                + speedStr.substring(index + 1, index + 5);
        return temp;
    }

    public static String add0Front(String vSourceString, int vOutputLen) {
        String strNewString;

        strNewString = vSourceString;

        for (int i = 0; i < vOutputLen - vSourceString.length(); i++) {
            strNewString = "0" + strNewString;
        }

        return strNewString;
    }
}
