package com.winjean.utils;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author winjean
 */
public class JasyptEncryptor {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("my_salt");
        String password = textEncryptor.encrypt("winjean");
        System.out.println(password);

        String originalText = textEncryptor.decrypt(password);
        System.out.println(originalText);
    }
}
