package com.atjl.validate.api;

/**
 * 公开接口
 * 内置：校验规则
 * e.g.
 */
public class ValidateRuleConstants {

    //可以为空
    public static String NULL_KEY = "null";

    //多规则分隔符
    public static String DFT_RULE_AND = "|";

    /**
     * 正数字
     */
    public static String RULE_NUEMRIC = "numeric";

    /**
     * 长度
     * e.g.
     * lengthmax(12) 最大长度 12
     */
    public static String RULE_LENGTH_MAX = "lengthmax(%s)";

    /**
     *
     */
    public static String RULE_NUM_MAX = "max(%s)";
    /**
     *
     */
    public static String RULE_NUM_MIN = "min(%s)";
    /**
     *
     */
    public static String RULE_NUM_PERIOD = "period(%s,%s)";


    /**
     * 长度
     * e.g. lengthmin(2) 最短长度 2
     */
    public static String RULE_LENGTH_MIN = "lengthmin(%s)";

    /**
     * 长度区间
     * e.g. length(3,32) 接收输入串 长度在3和32之间
     */
    public static String RULE_LENGTH_PERIOD = "length(%s,%s)";

    /**
     * 手机号
     * e.g.
     */
    public static String RULE_MOBILE = "mobile";

    // 邮箱
    public static String RULE_MAIL = "email";
    // 自定义正则
    public static String RULE_REG = "regex(%s)";

    /**
     * 日期
     * e.g. 2000年01月23日
     */
    public static String RULE_DATE = "date";
    /**
     * 时间
     * e.g. 12:32:23
     */
    public static String RULE_TIME = "time";

    /**
     * 日期+时间
     * e.g. 2000年01月23日12:32:23
     */
    public static String RULE_DATE_TIME = "datetime";
    /**
     * 接收0或1
     */
    public static String RULE_ZO = "zo";

    /**
     * 只能为指定值
     * e.g. in(X,Y,Z) 接收输入串 值为 X,Y或Z
     */
    public static String RULE_SPECIFIED_VALUES = "in(%s)";

    // 中文
    public static String RULE_CHINESE = "chinese";


}