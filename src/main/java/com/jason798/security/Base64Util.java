package com.jason798.security;

import java.io.IOException;

/**
 * base64
 */
public final class Base64Util {

    /**
     * 对字符进行64位编码加密
     *
     * @param args {@link String} 待加密的参数
     * @return {@link String} 加密后的数据
     */
    public static String encode(String args) {
        return encode(args.getBytes());
    }

    /**
     * 对byte数字进行64位编码加密
     *
     * @param args {@link Byte} 待加密的参数
     * @return {@link String} 加密后的数据
     */
    public static String encode(byte[] args) {
        if (args == null)
            return null;
        return new sun.misc.BASE64Encoder().encode(args);
    }

    /**
     * 对字符进行64位编码解密
     *
     * @param args {@link String} 待解密的参数
     * @return {@link Byte} 解密后的数据
     * @throws IOException
     */
    public static byte[] decode(String args) throws IOException {
        if (args == null)
            return null;
        return new sun.misc.BASE64Decoder().decodeBuffer(args);
    }

    /**
     * 对字符进行64位编码解密
     *
     * @param args {@link String} 待解密的参数
     * @return {@link String} 解密后的数据
     * @throws IOException
     */
    public static String decodeBuffer(String args) throws IOException {
        byte[] decodeByte = decode(args);
        return new String(decodeByte);
    }
}
