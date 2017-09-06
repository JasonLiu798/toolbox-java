package com.atjl.validate.util;

/**
 * sql语句生成 辅助类
 *
 * @author jasonliu
 */
public class ValidateSqlUtil {

    private ValidateSqlUtil() {
        super();
    }

    private static final String EXIST_SQL = "select count(1) from %s where %s = '%' ";
    private static final String TAB_COLUMN_CHK_SQL = "select %s from %s limit 1";

    public static String genExistSql(String table, String column, String value) {
        return String.format(EXIST_SQL, table, column, value);
    }

    public static String genTabColChkSql(String table, String column) {
        return String.format(TAB_COLUMN_CHK_SQL, column, table);
    }

}
