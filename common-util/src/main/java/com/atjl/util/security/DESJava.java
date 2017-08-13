package com.atjl.util.security;

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
	private DESJava(){
		throw new UnsupportedOperationException();
	}

    public static byte[] decrypt(byte[] message, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//key factory
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);//cipher init
            return cipher.doFinal(message);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException e) {
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

}
