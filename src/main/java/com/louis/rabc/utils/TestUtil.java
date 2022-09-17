package com.louis.rabc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONObject;
import com.louis.rabc.module.auth.entity.Auth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * @author laixiaoyi
 * @since 2022/9/16 11:08
 **/
@Component
@AllArgsConstructor
@Slf4j
public class TestUtil {

    private final Auth auth;

    public static String encrypted(String username, String password) {

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
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXhSNM8FIVFA+H3vfkS6DPkUtLl8jBSV+yFmbo\n" +
                "XtM6X7+LSKDYwYclIMjIO1cdT0Qcq2gcV9W/THRWdOgR314Y1E9axm7BDbatJkRAekbsuC/puZAz\n" +
                "d9x4b8PICXMzO5W9QT8BO4zPXfqKy8kTcjPvFhDpp/IkqbWq+yyOoHuXhQIDAQAB";
        String timeMillis = String.valueOf(System.currentTimeMillis());
        RSA rsa = new RSA(privateKey, publicKey);
        if (!StrUtil.isBlank(username)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("password", password);
            jsonObject.set("timeMillis", timeMillis);
            jsonObject.set("phone", "17605609116");
            System.out.println("将要加密的密文为： " + "17605609116");
//            System.out.println("将要加密的密文为： " + jsonObject.toString());
            String encrypt = rsa.encryptBase64("17605609116", KeyType.PublicKey);
//            String encrypt = rsa.encryptBase64(jsonObject.toString(), KeyType.PublicKey);
            System.out.println("rsa加密后的密文为：" + encrypt);
            String decrypt = rsa.decryptStr(encrypt, KeyType.PrivateKey);
            System.out.println("解密后的密文为：" + decrypt);
        }
        return null;
    }

    public void loginEncrypt() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("timeMillis", String.valueOf(System.currentTimeMillis()));
        jsonObject.set("password", "123456");
        RSA rsa = new RSA(auth.getPrivateKey(), auth.getPublicKey());
        log.info("jsonObject ===> {}", jsonObject);
        String encrypt = rsa.encryptBase64(jsonObject.toString(), KeyType.PublicKey);
        log.info("encrypt ===> {}", encrypt);
        String decrypt = rsa.decryptStr(encrypt, KeyType.PrivateKey);
        log.info("decrypt ===> {}", decrypt);
    }

    public static void main(String[] args) {
        TestUtil.encrypted("louis", "123456");
        //生成公私钥对
    }
}
