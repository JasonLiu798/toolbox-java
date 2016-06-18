package com.jason798.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5
 */
public class MD5Helper {

    /**
     * MD5 加密方法 默认32位加密
     *
     * @param data
     * @return {@link String} 密文
     */
    public static String encode(String data) {
        return encode(data, 32);
    }

    /**
     * MD5 加密方法
     *
     * @param data
     * @param bit
     * @return {@link String} 密文
     */
    public static String encode(String data, int bit) {
        return encode(data.getBytes(), bit);
    }

    /**
     * MD5 加密方法
     *
     * @param data
     * @param bit
     * @return {@link String} 密文
     * @throws UnsupportedEncodingException
     */
    public static String encode(String data, int bit, String code) throws UnsupportedEncodingException {
        return encode(data.getBytes(code), bit);
    }

    /**
     * MD5 加密方法
     *
     * @param data
     * @return {@link String} 密文
     */
    public static String encode(byte[] data, int bit) {
        String ciphertext = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            if (bit == 32) {
                // 32位加密
                ciphertext = buf.toString();
            }
            if (bit == 16) {
                // 16位的加密
                ciphertext = buf.toString().substring(8, 24);
            }
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return ciphertext;
    }
}
