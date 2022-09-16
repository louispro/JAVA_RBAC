package com.louis.rabc.utils;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.PublicKey;

/**
 * @author laixiaoyi
 * @since 2022/9/16 11:08
 **/
public class TestUtil {

    public static String encrypted(String phone, String password, String publicKey) {
        String timeMillis = String.valueOf(System.currentTimeMillis());
        RSA rsa = new RSA();
        rsa.setPublicKey(new PublicKey() {
            @Override
            public String getAlgorithm() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }
        });
        return null;
    }
}
