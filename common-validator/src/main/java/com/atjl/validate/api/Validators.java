package com.atjl.validate.api;


public class Validators {
    /**
     * 无参数规则
     */
    public static final String EMAIL = "email";//email
    public static final String IPV4 = "ip";//ipv4
    public static final String OPTIONAL = "optional";//可选字段
    public static final String REQUIRE = "require";//必输字段
    public static final String TIMESTAMP = "Timestamp";//时间戳
    public static final String URL = "url";//url
    public static final String UUID = "uuid";//uuid
    public static final String ISBOOLEAN = "boolean";//boolean
    public static final String ALPHANUMDASH = "alphaNumDash";


    /**
     * anyOf(aa,bb,cc)
     */
    public static final String ANY_OF = "anyOf";
    public static final String ANY_OF_REG = "^anyOf\\([]\\)$";

    /**
     * 存在性规则
     * e.g.
     * exist(tab_1,col_1)
     * 校验tab_1表是否存在col_1字段是否存在值
     * exist(tab_1)
     * col字段不填值，默认设置列名为 表名+_ID
     */
    public static final String EXIST = "exist";
    public static final String EXIST_REG = "^exist\\([\\s+,\\s+|\\s+]\\)$";


}
