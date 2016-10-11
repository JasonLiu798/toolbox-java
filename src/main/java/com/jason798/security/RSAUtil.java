package com.jason798.security;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sun.org.apache.xml.internal.security.utils.Base64;

@SuppressWarnings("restriction")
public class RSAUtil {

    private static final String module = "5m9m14XH3oqLJ8bNGw9e4rGpXpcktv9MSkHSVFVMjHbfv" +
            "+SJ5v0ubqQxa5YjLN4vc49z7SVju8s0X4gZ6AzZTn06jzWOgyPRV54Q4I0DCYadWW4Ze3e" +
            "+BOtwgVU1Og3qHKn8vygoj40J6U85Z/PTJu3hN1m75Zr195ju7g9v4Hk=";
    private static final String exponentString = "AQAB";
    private static final String delement =
            "vmaYHEbPAgOJvaEXQl+t8DQKFT1fudEysTy31LTyXjGu6XiltXXHUuZaa2IPyHgBz0Nd7znwsW" +
                    "/S44iql0Fen1kzKioEL3svANui63O3o5xdDeExVM6zOf1wUUh/oldovPweChyoAdMtUzgvCbJk1sYDJf" +
                    "++Nr0FeNW1RB1XG30=";
    public static String modules = null;//big prime
    public static String zhishu = null;//index
    private static String privateKey = null;//private key


    public static void generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");// ,new
            // BouncyCastleProvider()
            final int KEY_SIZE = 1024;// 可以更改，但是不要太大，否则效率会低
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.genKeyPair();
            RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
            BigInteger pubModBytes = pubKey.getModulus();
            BigInteger zhish = pubKey.getPublicExponent();
            BigInteger mao = priKey.getModulus();
            BigInteger msiyao = priKey.getPrivateExponent();
            String bigmoudle = Base64.encode(pubModBytes);
            String bigzhishu = Base64.encode(zhish);
            String pmao = Base64.encode(mao);
            String psiyao = Base64.encode(msiyao);
            // 生成公钥 PublicKey
            modules = bigmoudle;
            // System.out.println(modules+"素数");
            zhishu = bigzhishu;
            // System.out.println(zhishu+"指数");
            privateKey = psiyao;
            // System.out.println(privateKey+"私钥");
            /*
             * RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
			 * System.out.println("公钥:"+pubKey); RSAPrivateKey priKey =
			 * (RSAPrivateKey) keyPair.getPrivate();
			 * System.out.println("私钥:"+priKey); byte[] pubModBytes =
			 * pubKey.getModulus().toByteArray(); BigInteger pubeponent =
			 * pubKey.getPublicExponent();
			 * 
			 * String modulusStr = pubModBytes.toString(); String pubeponentStr
			 * = pubeponent.toString(); System.out.println("???????");
			 * System.out.println("字符串"+pubeponentStr);
			 * System.out.println("???????");
			 * System.out.println("大素数:"+pubKey.getModulus()); RSAPublicKey py =
			 * (RSAPublicKey)pubKey ; BigInteger pubModBytes = py.getModulus();
			 * BigInteger zhishu = py.getPublicExponent(); String mmm =
			 * Base64.encode(pubModBytes); String nnn = Base64.encode(zhishu);
			 * System.out.println(mmm +"搞定"); System.out.println(nnn +"指数搞定");
			 * RSAPrivateKey priKey = (RSAPrivateKey) privKey; BigInteger priva
			 * = priKey.getPrivateExponent(); String pri = Base64.encode(priva);
			 * System.out.println(pri +"私钥搞定");
			 */
            // 生成私钥

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * decode
     *
     * @param encryptedStr cryptograph
     * @param delemen      String for private key
     * @param module       big prime
     * @return
     */
    public String dencrypt(String encryptedStr, String delemen, String module) {
        byte[] encrypted = null;
        if (encryptedStr != null && !encryptedStr.equals("")) {

            encrypted = GetByteFromString(encryptedStr);
        }
        try {
            byte[] expBytes = Base64.decode(delemen);
            byte[] modBytes = Base64.decode(module);

            BigInteger modules = new BigInteger(1, modBytes);
            BigInteger exponent = new BigInteger(1, expBytes);

            KeyFactory factory = KeyFactory.getInstance("RSA",
                    new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA",
                    new BouncyCastleProvider());
            RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(modules,
                    exponent);
            PrivateKey privKey = factory.generatePrivate(privSpec);
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            if (encrypted != null) {
                byte[] decrypted = null;
                try {
                    decrypted = cipher.doFinal(encrypted);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String deJieMi = null;
                deJieMi = new String(decrypted);
                return deJieMi;
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * string to byte[]
     *
     * @param miWen
     * @return
     */
    private byte[] GetByteFromString(String miWen) {
        byte[] miWenbyte = null;
        if (miWen != null && miWen.length() > 0) {
            String[] strMi = null;
            strMi = miWen.split(",");

            int length = 0;
            length = strMi.length;
            miWenbyte = new byte[length];
            for (int i = 0; i < length; i++) {
                int byteValue = Integer.parseInt(strMi[i]);
                miWenbyte[i] = (byte) byteValue;
            }
        }
        return miWenbyte;
    }

    /**
     * encode
     *
     * @param string2encode String to encode
     * @param modul         big prime
     * @param exponentStrin index
     */
    public static String encrypt(String string2encode, String modul,
                                 String exponentStrin) {
        try {
            byte[] modulusBytes = Base64.decode(modul);
            byte[] exponentBytes = Base64.decode(exponentStrin);
            BigInteger modulus = new BigInteger(1, modulusBytes);
            BigInteger exponent = new BigInteger(1, exponentBytes);
            RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus, exponent);
            KeyFactory fact = KeyFactory.getInstance("RSA",
                    new BouncyCastleProvider());
            PublicKey pubKey = fact.generatePublic(rsaPubKey);
            Cipher cipher = Cipher.getInstance("RSA",
                    new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            // 得到公钥
            byte[] cipherData = cipher.doFinal(string2encode.getBytes());
            String m = null;
            m = TransferBackMiwen(cipherData);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * byte to String
     *
     * @param miWen
     * @return
     */
    private static String TransferBackMiwen(byte[] miWen) {
        String backMiwen = null;
        StringBuffer backMi = new StringBuffer();
        int length = 0;
        if (miWen != null) {
            length = miWen.length;
            for (int i = 0; i < length; i++) {
                String tempMi = miWen[i] + ",";
                backMi = backMi.append(tempMi);
            }
        }
        if (backMi != null)
            backMiwen = backMi.toString();
        return backMiwen;
    }
}
