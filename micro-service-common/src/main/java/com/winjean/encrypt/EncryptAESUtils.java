package com.winjean.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author ：winjean
 * @date ：Created in 2020/7/9 15:07
 * @description： AES 本身就是为了取代 DES 的，AES 具有更好的 安全性、效率 和 灵活性。
 * @modified By：
 * @version: $version$
 */
public class EncryptAESUtils {

    /*
     * 加密（对外暴露）
     */
    public static String encryptData(String privateKey, String content) throws Exception {
        KeyGenerator keygen = getKeyGenerator(privateKey);
        SecretKey key = new SecretKeySpec(keygen.generateKey().getEncoded(), "AES");
        return Base64.getEncoder().encodeToString(encrypt(key, content.getBytes("UTF-8")));
    }

    /*
     * 解密（对外暴露）
     */
    public static String decryptData(String privateKey, String content) throws Exception {
        KeyGenerator keygen = getKeyGenerator(privateKey);
        SecretKey key = new SecretKeySpec(keygen.generateKey().getEncoded(), "AES");
        return new String(decrypt(key, Base64.getDecoder().decode(content)), "UTF-8");
    }

    private static KeyGenerator getKeyGenerator(String privateKey) throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(privateKey.getBytes());
        keygen.init(128, secureRandom);
        return keygen;
    }

    private static byte[] encrypt(Key key, byte[] srcBytes) {
        if (key != null) {
            try {
                // Cipher负责完成加密或解密工作，基于AES
                Cipher cipher = Cipher.getInstance("AES");
                // 对Cipher对象进行初始化
                cipher.init(Cipher.ENCRYPT_MODE, key);
                // 加密，保存并返回
                return cipher.doFinal(srcBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static byte[] decrypt(Key key, byte[] encBytes) {
        if (key != null) {
            try {
                Cipher cipher = Cipher.getInstance("AES");
                //对Cipher对象进行初始化
                cipher.init(Cipher.DECRYPT_MODE, key);
                //解密
                return cipher.doFinal(encBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        String privateKey = "ABC";
        String content = "ASD456";
        String m = encryptData(privateKey, content);
        System.out.println("根据私钥：" + privateKey + "，加密后的密文是：" + m);
        System.out.println("根据私钥：" + privateKey + "，解密后的明文是：" + decryptData(privateKey, m));
    }
}
