package com.louis.rabc.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import sun.misc.BASE64Encoder;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author laixiaoyi
 * @since 2022/9/16 14:40
 **/
public class RSAUtil {
    /**
     * 类型
     */
    public static final String ENCRYPT_TYPE = "RSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 公钥加密
     * @param content 要加密的内容
     * @param publicKey 公钥
     */
    public static String encrypt(String content, PublicKey publicKey) {
        try{
            RSA rsa = new RSA(null,publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥加密
     * @param content 要加密的内容
     * @param publicKey 公钥
     */
    public static String encrypt(String content, String publicKey) {
        try{
            RSA rsa = new RSA(null,publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 私钥解密
     * @param content 要解密的内容
     * @param privateKey 私钥
     */
    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            RSA rsa = new RSA(privateKey,null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥解密
     * @param content 要解密的内容
     * @param privateKey 私钥
     */
    public static String decrypt(String content, String privateKey) {
        try {
            RSA rsa = new RSA(privateKey,null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取公私钥-请获取一次后保存公私钥使用
     * @return
     */
    public static Map<String,String> generateKeyPair() {
        try {
            KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE);
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
            byte[] pubEncBytes = publicKey.getEncoded();
            byte[] priEncBytes = privateKey.getEncoded();
            // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
            String pubEncBase64 = new BASE64Encoder().encode(pubEncBytes);
            String priEncBase64 = new BASE64Encoder().encode(priEncBytes);
            Map<String, String> map = new HashMap<String, String>(2);
            map.put(PUBLIC_KEY,pubEncBase64);
            map.put(PRIVATE_KEY,priEncBase64);
            return map;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, String> keyMap = RSAUtil.generateKeyPair();
//        String privateKey = keyMap.get(PRIVATE_KEY);
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJeFI0zwUhUUD4fe9+RLoM+RS0uX\n" +
                "yMFJX7IWZuhe0zpfv4tIoNjBhyUgyMg7Vx1PRByraBxX1b9MdFZ06BHfXhjUT1rGbsENtq0mREB6\n" +
                "Ruy4L+m5kDN33Hhvw8gJczM7lb1BPwE7jM9d+orLyRNyM+8WEOmn8iSptar7LI6ge5eFAgMBAAEC\n" +
                "gYBuUqxfSuQZIOAmB/zqpv5R4/K0+bFcuvYIjzMiolieOjUXTyx+ZnrmV7aZxEsrthlb3poReQrT\n" +
                "HPHgOYL38HFr9Gf39S+tVFCjoSAkuRxV7q6mXo94zQ1hhJe7WAbYZqUOdhf+7r3WG4mZSS2C10OA\n" +
                "Thq27xO8mJo5V1Jkd56PiQJBAOZMWS+9a7hEOTKHYMhWl6mgYk7ulVNov2RIDSjYEPfVp68F28qF\n" +
                "I14vpD2sdkyxgCtWFxWPomHa7Pgky2HDdLcCQQCobhYxt6k9d2OO8bUqoa2MzaB5gOgDeos6qnVL\n" +
                "6amJaRT20o3Wa+o5XqeqBrQll/AayxrLaNoXO9iqty8JGPGjAkANwsohfCQPoqP4TPaPgf0V+atm\n" +
                "JANwNHYbO+Ltadu5s16Nqr/zXfk7C4W42bFH6+NIs6A1fZuqVTaLrUcp1FAJAkAA6jgspPbCYrej\n" +
                "uyRkyuQKc8N/ZXfdLPk8pFhmxSoqmOuIO7SRiZyIGz+F6OtpmN2xB5FpsXQd1DFlHAGhZwVJAkAe\n" +
                "RiHb+8l31KEnJ7Or9y9qg2qVNV4YMaQxFVvSPsdX1ChLTmQJf9sZvnP451GdaRpfzo9NqFm21cNR\n" +
                "s39+f8HG";

        System.out.println("私钥：" + privateKey);
//        String publicKey = keyMap.get(PUBLIC_KEY);
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXhSNM8FIVFA+H3vfkS6DPkUtLl8jBSV+yFmbo\n" +
                "XtM6X7+LSKDYwYclIMjIO1cdT0Qcq2gcV9W/THRWdOgR314Y1E9axm7BDbatJkRAekbsuC/puZAz\n" +
                "d9x4b8PICXMzO5W9QT8BO4zPXfqKy8kTcjPvFhDpp/IkqbWq+yyOoHuXhQIDAQAB";
        System.out.println("公钥：" + publicKey);
        String encrypt = RSAUtil.encrypt("louis", publicKey);
        String decrypt = RSAUtil.decrypt(encrypt, privateKey);
        System.out.println("encrypt:" + encrypt);
        System.out.println("decrypt:" + decrypt);
    }
}
