package com.jason.security;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES
 */
public class DESJava {

    private String encode = "UTF-8";

    public static byte[] decrypt(byte[] message, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//key factory
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);//cipher init
            return cipher.doFinal(message);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * string to byte
     *
     * @param miWen
     * @return
     */
    private static byte[] GetByteFromString(String miWen) {

        byte[] miWenbyte = null;
        if (miWen != null && miWen.length() > 0) {
            String[] strMi = null;
            strMi = miWen.split(",");

            int length = 0;
            length = strMi.length;
            miWenbyte = new byte[length];
            for (int i = 0; i < length; i++) {
                //将字符串变为byte数组
                int byteValue = Integer.parseInt(strMi[i]);
                miWenbyte[i] = (byte) byteValue;

            }
        }
        return miWenbyte;
    }

    /**
     * encrypt
     *
     * @param content2encrypt
     * @return
     */
    public static byte[] encrypt(byte[] content2encrypt, String key) {
        Cipher cipher = null;
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

            cipher = Cipher.getInstance("DES");// do encrypt
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,random);//init cipher
            return cipher.doFinal(content2encrypt);
        } catch (Exception e) {
            throw new RuntimeException("Error Cause:" + e);
        } finally {
            cipher = null;
        }
    }


    public static void main(String[] args) {
        String str = "abcde";
        String key = "95880288201091325707433253118984263478572987735494687" +
                "588750185795377577721630844788736994473060344662006164119605" +
                "74122434059469100235892702736860872901247123456";
        byte[] res = encrypt(str.getBytes(), key);
        for (byte r : res) {
            System.out.println(r);
        }

        String dec = new String(res, Charset.defaultCharset());
        System.out.println("de str " + dec);

        byte[] dres = decrypt(res, key);
        String aaa = new String(dres, Charset.defaultCharset());
        System.out.println("de res " + aaa);
    }
}
