package com.atjl.validate.domain.constants;

/**
 * 内部 常量
 *
 */
public class ValidateConstants {

    public static final String VALIDATE_PROP_FILE = "validate";
    //aop method
    public static String CHKFUNC="check";

    //多规则分隔符
    public static String RULE_SEP = " ";
    //规则内空格转义
    public static String SPACE_CHAR = "&#xA0;";

    //可以为空
    public static String NULL_KEY = "null";
    //正数字
    public static String NUEMRIC= "^[1-9]\\d*|0$";
    // 手机号
	public static String MOBILE = "^1\\d{10}$";
    // 邮箱
    public static String MAIL_KEY = "email";
    public static String MAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    // 自定义正则
    public static String REG_KEY="regex";


    public static String REG="reg:";
    public static int REG_LEN=REG.length();
    // 时分
    public static String TIME_KEY = "time";
    // 时分号验证表达式
    public static String TIME = "^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$";
    // zero or one
    public static String ZO_KEY = "zo";
    public static String ZO = "^0|1$";//"(^0$|^1$)";
    // 中文
    public static String REGEXP_CHINESE_KEY = "chinese";
    // 中文验证表达式
    public static String REGEXP_CHINESE_VALUE = "[\u4E00-\uFA29]+";

	// code
	public static String SYSTEM_CODE_STRING = "code";
	// message
	public static String SYSTEM_MESSAGE_STRING = "message";








    // 周期验证

    // 周期验证
    // public static String REGEXP_PERIOD_KEY = "period";

}