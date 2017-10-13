package com.atjl.validate.util;

import com.atjl.util.character.StringCheckUtil;

/**
 * sql语句生成 辅助类
 *
 * @author jasonliu
 */
public class ValidateSqlUtil {

    private ValidateSqlUtil() {
        super();
    }


    public static final String CNT_COL = "cnt";


    private static final String EXIST_SQL = "select count(1) %s from %s where %s = '%s' ";
    private static final String EXIST_SQL_EXT = "select count(1) %s from %s where %s = '%s' %s ";
    private static final String TAB_COLUMN_CHK_SQL = "select %s from %s limit 1";
    private static final String TAB_COLUMN_CHK_SQL_EXT = "select %s from %s where 1=1 %s limit 1";

    public static String genExistSql(String table, String column, String otherConds, String value) {
        if (StringCheckUtil.isEmpty(otherConds)) {
            return String.format(EXIST_SQL, CNT_COL, table, column, value);
        } else {
            return String.format(EXIST_SQL_EXT, CNT_COL, table, column, value, otherConds);
        }
    }

    public static String genTabColChkSql(String table, String column, String otherConds) {
        if (StringCheckUtil.isEmpty(otherConds)) {
            return String.format(TAB_COLUMN_CHK_SQL, column, table);
        } else {
            return String.format(TAB_COLUMN_CHK_SQL_EXT, column, table, otherConds);
        }
    }

}
