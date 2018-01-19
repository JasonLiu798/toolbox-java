package com.atjl.util.character;

import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用正则
 */
public final class RegexUtil {
    private RegexUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(RegexUtil.class);

    public static final String FRAG_SPACE = "\\s";
    public static final String FRAG_SPACES_OR_NE = "\\s*";
    public static final String FRAG_NOT_SPACE = "\\S";
    public static final String FRAG_NOT_SPACES = "\\S+";
    public static final String DOT_SEP_NO_REX = "\\.";

    // email
    public static final String REGEXP_MAIL_VALUE = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    //url
    public static final String REG_URL = "^[a-z]+://([^/:]+)(:[0-9]+)?(\\/.*)?$";
    public static final String REG_UUID = "^[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}$";

    // phone
    public static final String REGEXP_PHONE_VALUE = "^\\d{11}$";

    public static final String REGEXP_POSITIVE = "^[1-9]{1}\\d*$";

    public static final String REG_ALPHA_NUM_DASH = "^[a-zA-Z0-9_]+$";//大小写字母，数字，下划线
    public static final String REG_ALPHA_NUM = "^[a-zA-Z0-9]+$";//大小写字母，数字，
    public static final String REG_ALPHA = "^[a-zA-Z]+$";//大小写字母

    public static final String REG_CHINESE = "^[\\u4e00-\\uFA29]+$";//中文

    public static final String REGEXP_NATURAL = "^\\d+$";

    public static final String REGEXP_INTEGER = "^-*\\d+$";

    public static final String REG_IP = "^-*\\d+$";

    //    public static final String REG_RATIONAL_NUM = "^-{0,1}([1-9]\\d*|0)\\.{0,1}\\d*|0\\.\\d*[1-9]\\d*$";
    public static final String REG_RATIONAL_NUM = "^-{0,1}([1-9]\\d*|0)\\.{0,1}\\d*$";

    public static final String TRUE = "true";
    public static final String FALSE = "false";


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

    public static Pattern getPattern(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern;
    }

    public static String getDigits(String raw) {
//        String regex = "\\d=([^(,+})]*)";
        String regex = "(\\d+)";
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(raw);
        StringBuilder res = new StringBuilder();
        while (mMatcher.find()) {
            res.append(mMatcher.group(1));
        }
        return res.toString();
    }

    public static String replaceAll(String raw, String regex, String target) {
        if (StringCheckUtil.isExistEmpty(raw, regex)) {
            return raw;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(raw);
        if (m.find()) {
            if(target==null){
                target="";
            }
            return m.replaceAll(target);
        }
        return raw;
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

    public static boolean chk(String raw, String regex) {
        Matcher matcher = getMatcher(regex, raw);
        return matcher != null && matcher.matches();
    }

    /**
     * 校验手机号
     *
     * @param str
     * @return boolean
     */
    public static boolean isMobile(String str) {
        return chk(str, REGEXP_PHONE_VALUE);
    }

    /**
     * 校验邮箱
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmail(String str) {
        return chk(str, REGEXP_MAIL_VALUE);
    }

    public static boolean isURL(String str) {
        return chk(str, REG_URL);
    }

    public static boolean isUUID(String str) {
        return chk(str, REG_UUID);
    }

    /**
     * positive number
     * 1~9999...999
     *
     * @param str check string
     * @return is or not
     */
    public static boolean isPositive(String str) {
        return chk(str, REGEXP_POSITIVE);
    }

    /**
     * is a natural
     * 0~9999...99999
     *
     * @param str test string
     * @return is or not
     */
    public static boolean isNatural(String str) {
        return chk(str, REGEXP_NATURAL);
    }

    /**
     * 是否中文
     */
    public static boolean isChinese(String str) {
        return chk(str, REG_CHINESE);
    }

    /**
     * 是否整型（包含负数）
     * int.min ~ int.max
     *
     * @param str test string
     * @return
     */
    public static boolean isInteger(String str) {
        return chk(str, REGEXP_INTEGER);
    }


    public static boolean isIPV4(String raw) {
        if (StringCheckUtil.isEmpty(raw)) {
            return false;
        }
        if (!raw.contains(".")) {
            return false;
        }
        String[] parts = raw.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String p : parts) {
            if (p.length() > 3) {
                return false;
            }
            if (!RegexUtil.isDigit(p)) {
                return false;
            }
            if (p.charAt(0) == '0' && p.length() > 1) {
                return false;
            }
            int pn = Integer.parseInt(p);
            if (pn < 0 || pn > 255) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param raw
     * @return
     */
    public static boolean isIPV6(String raw) {
        if (StringCheckUtil.isEmpty(raw)) {
            return false;
        }
        if (raw.length() <= 1) {
            return false;
        }
        if (!raw.contains(":")) {
            return false;
        }
        String[] parts = raw.split("\\:");
        if (parts.length > 8) {
            return false;
        }

        int blank = 0;
        for (String p : parts) {
            if (StringCheckUtil.isEmpty(p)) {
                blank++;
            } else {
                try {
                    int val = Integer.parseInt(p, 16);
                    if (val < 0 || val > 65535) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }

        if (blank < 2) {
            return true;
        } else if (blank == 2 && !StringCheckUtil.isEmpty(parts[0]) && !StringCheckUtil.isEmpty(parts[1])) {
            return true;
        }
        return false;
    }

    /**
     * 是否有理数
     *
     * @param str
     * @return
     */
    public static boolean isRationalNum(String str) {
        return chk(str, REG_RATIONAL_NUM);
    }

    public static boolean isAlpha(String str) {
        return chk(str, REG_ALPHA);
    }

    public static boolean isAlphaNum(String str) {
        return chk(str, REG_ALPHA_NUM);
    }

    public static boolean isAlphaNumDash(String str) {
        return chk(str, REG_ALPHA_NUM_DASH);
    }

    public static boolean isAlphaNumDash(String... str) {
        if (CollectionUtil.isEmpty(str)) {
            return false;
        }
        for (String s : str) {
            if (!chk(s, REG_ALPHA_NUM_DASH)) {
                return false;
            }
        }
        return true;
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
        if (StringCheckUtil.isEmpty(str)) {
            return false;
        }
        String tmp = str.toLowerCase();
        return tmp.equals(TRUE) || tmp.equals(FALSE);
    }


    /**
     * 只保留数字、字母、汉字、下划线
     *
     * @param str
     * @return
     */
    public static String leftNumStrCNUL(String str) {
        String pt2 = "[^a-zA-Z0-9_\\u4e00-\\u9fa5]";
        if (StringCheckUtil.isEmpty(str)) {
            str = str.replaceAll(pt2, "");
        }
        return str;
    }
}
