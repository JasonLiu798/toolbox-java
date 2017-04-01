package com.jason798.security;

import java.util.zip.CRC32;

/**
 * crc32
 */
public class Crc32Util {

    /**
     * 获取字符串crc32
     *
     * @param str
     * @return long
     */
    public static int getCrc32ForPhp(String str) {
        CRC32 crc32 = new CRC32();
        crc32.update(str.toUpperCase().getBytes());
        return (int) crc32.getValue();
    }

    /**
     * 获取字符串crc32
     *
     * @param str
     * @return long
     */
    public static long getCrc32(String str) {
        CRC32 crc32 = new CRC32();
        crc32.update(str.toUpperCase().getBytes());
        return crc32.getValue();
    }

}
