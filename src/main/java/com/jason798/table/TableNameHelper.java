package com.jason798.table;

import com.jason798.character.StringHelper;
import com.jason798.security.Crc32Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * db table dispatcher
 */
public class TableNameHelper {
    private static Logger logger = LoggerFactory.getLogger(TableNameHelper.class);

    /**
     * mod type
     **/
    public static String TABLE_SPIT_MOD = "mod";

    /**
     * hash type
     **/
    public static String TABLE_SPIT_HASH = "hash";

    /**
     * crc32 type
     **/
    public static String TABLE_SPIT_CRC32 = "crc32";

    /**
     * mobile type
     **/
    public static String TABLE_SPIT_MOBILE = "mobile";

    public static int DFT_TABLE_COUNT = 10;

    /**
     * 获取表名
     *
     * @param value     值
     * @param tableName 表名
     * @param tableRule 分表规则
     * @param tablenum  表个数
     * @return String 分表名
     */
    public static String getTableName(String value, String tableName, String tableRule, int tablenum) {
        long suffix = 0L;
        if (StringHelper.isEqualString(tableRule, TABLE_SPIT_MOD)) {
            suffix = Long.parseLong(value) % 10 + 1;
        }
        if (StringHelper.isEqualString(tableRule, TABLE_SPIT_HASH)) {
            try {
                suffix = getHash(value) % 10 + 1;
            } catch (UnsupportedEncodingException e) {
                logger.error("hash分表规则转换错误");
            }
        }
        if (StringHelper.isEqualString(tableRule, TABLE_SPIT_CRC32)) {
            suffix = Crc32Helper.getCrc32(value) % (tablenum == 0 ? 10 : tablenum) + 1;
        }
        if (StringHelper.isEqualString(tableRule, TABLE_SPIT_MOBILE)) {
            char[] mobiles = value.substring(value.length() - 4, value.length()).toCharArray();
            suffix = (Integer.parseInt(String.valueOf(mobiles[0])) + Integer.parseInt(String.valueOf(mobiles[1])) + Integer.parseInt(String.valueOf(mobiles[2]))
                    + Integer.parseInt(String.valueOf(mobiles[3]))) % 10 + 1;
        }
        return tableName + "_" + suffix;
    }

    private static int getHash(String key) throws UnsupportedEncodingException {
        key = key.toUpperCase();
        byte[] byBuffer = new byte[4];
        byBuffer = key.getBytes("utf-8");
        int l = 0;
        int len = byBuffer.length > 4 ? 4 : byBuffer.length;
        for (int i = 0; i < len; i++) {
            l = l + (byBuffer[i] & 0xff);
        }
        return l;
    }


}
