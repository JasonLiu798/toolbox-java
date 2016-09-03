package com.jason798.character;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用正则
 */
public final class RegexUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegexUtil.class);

    /**
     * email
     */
    public static final String REGEXP_MAIL_VALUE = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    /**
     * phone
     */
    public static final String REGEXP_PHONE_VALUE = "^\\d{11}$";

    public static final String REGEXP_POSITIVE = "^[1-9]{1}\\d*$";

    public static final String REGEXP_NATURAL = "^\\d+$";

    public static final String REGEXP_INTEGER = "^-*\\d+$";

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * 只保留数字、字母、汉字、下划线
     *
     * @param str
     * @return
     */
    public static String leftNumStrCNUL(String str) {
        String pt2 = "[^a-zA-Z0-9_\\u4e00-\\u9fa5]";
        if (StringUtil.isEmpty(str)) {
            str = str.replaceAll(pt2, "");
        }
        return str;
    }

    /**
     * 返回对应的Matcher
     *
     * @param regex     正则表达式
     * @param targetStr 目标字符串
     * @return Matcher
     */
    public static Matcher getMatcher(String regex, String targetStr) {
        Pattern pattern = Pattern.compile(regex);
        if (targetStr == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(targetStr);
        return matcher;
    }

    /**
     * 校验手机号
     *
     * @param str
     * @return boolean
     */
    public static boolean isMobile(String str) {
        return getMatcher(REGEXP_PHONE_VALUE, str).matches();
    }

    /**
     * 校验邮箱
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmail(String str) {
        return getMatcher(REGEXP_MAIL_VALUE, str).matches();
    }

    /**
     * 是否数字
     *
     * @param str
     * @return
     */
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

    /**
     * positive number
     * 1~9999...999
     *
     * @param str check string
     * @return is or not
     */
    public static boolean isPositive(String str) {
        //boolean flag = true;
        Matcher matcher = getMatcher(REGEXP_POSITIVE, str);
        if (matcher != null) {
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * is a natural
     * 0~9999...99999
     *
     * @param str test string
     * @return is or not
     */
    public static boolean isNatural(String str) {
        Matcher matcher = getMatcher(REGEXP_NATURAL, str);
        if (matcher != null) {
            return matcher.matches();
        } else {
            return false;
        }
    }


    /**
     * is integer
     *
     * @param str test string
     * @return
     */
    public static boolean isInteger(String str) {
        Matcher matcher = getMatcher(REGEXP_INTEGER, str);
        if (matcher != null) {
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * is boolean
     * true value:
     * true,True,tRue
     *
     * @param str test string
     * @return is or not boolean
     */
    public static boolean isBoolean(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        String tmp = str.toLowerCase();
        if (tmp.equals(TRUE) || tmp.equals(FALSE)) {
            return true;
        }
        return false;
    }
}
