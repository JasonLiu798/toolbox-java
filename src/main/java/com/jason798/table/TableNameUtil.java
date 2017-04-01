package com.jason798.table;

import com.jason798.character.StringCheckUtil;
import com.jason798.security.Crc32Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;

/**
 * db table dispatcher
 */
public class TableNameUtil {
    private static Logger logger = LoggerFactory.getLogger(TableNameUtil.class);

    /**
     * mod type
     **/
    public static final String TABLE_SPIT_MOD = "mod";

    /**
     * hash type
     **/
    public static final String TABLE_SPIT_HASH = "hash";

    /**
     * crc32 type
     **/
    public static final String TABLE_SPIT_CRC32 = "crc32";

    /**
     * mobile type
     **/
    public static final String TABLE_SPIT_MOBILE = "mobile";

    public static int DFT_TABLE_COUNT = 10;


    public static String getTableName(String value, String tableName, String tableRule, int tablenum) {
        long suffix = 0L;
        if (StringCheckUtil.equal(tableRule, TABLE_SPIT_MOD)) {
            suffix = Long.parseLong(value) % 10 + 1;
        }
        if (StringCheckUtil.equal(tableRule, TABLE_SPIT_HASH)) {
            try {
                suffix = getHash(value) % 10 + 1;
            } catch (UnsupportedEncodingException e) {
                logger.error("hash table dispatch fail");
            }
        }
        if (StringCheckUtil.equal(tableRule, TABLE_SPIT_CRC32)) {
            suffix = Crc32Util.getCrc32(value) % (tablenum == 0 ? 10 : tablenum) + 1;
        }
        if (StringCheckUtil.equal(tableRule, TABLE_SPIT_MOBILE)) {
            char[] mobiles = value.substring(value.length() - 4, value.length()).toCharArray();
            suffix = (Integer.parseInt(String.valueOf(mobiles[0])) + Integer.parseInt(String.valueOf(mobiles[1])) + Integer.parseInt(String.valueOf(mobiles[2]))
                    + Integer.parseInt(String.valueOf(mobiles[3]))) % 10 + 1;
        }
        return tableName + "_" + suffix;
    }

    private static int getHash(String key) throws UnsupportedEncodingException {
        key = key.toUpperCase();
        byte[] byBuffer = null;//new byte[4];
        byBuffer = key.getBytes("utf-8");
        int l = 0;
        int len = byBuffer.length > 4 ? 4 : byBuffer.length;
        for (int i = 0; i < len; i++) {
            l = l + (byBuffer[i] & 0xff);
        }
        return l;
    }


}
