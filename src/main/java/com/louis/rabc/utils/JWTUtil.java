package com.louis.rabc.utils;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {

    private static final String SECRET_KEY = "SECRET_KEY";

    /**
     * 生成token
     *
     * @param username  用户名
     * @param role      角色
     * @return {@link String}
     */
    public static String generateToken(String username, String role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String token = jwtBuilder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("username", username)
                .claim("role", role)
                .setSubject("authentication-login")
                .setExpiration(DateUtil.offsetHour(new Date(), 2))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, JWTUtil.SECRET_KEY)
                .compact();
        return token;
    }

    /**
     * 解密token
     *
     * @return {@link String}
     */
    public static Map<String, String> checkToken(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(JWTUtil.SECRET_KEY).parseClaimsJws(token);
//        Claims claims = claimsJws.getBody();
//        Map<String, String> map = new HashMap<>(2);
//        map.put("username", claims.get("username", String.class));
        return null;
    }
}
