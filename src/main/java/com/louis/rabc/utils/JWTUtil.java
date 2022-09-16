package com.louis.rabc.utils;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {

    /**
     * 生成token
     *
     * @param username  用户名
     * @param role      角色
     * @param publicKey 公钥
     * @return {@link String}
     */
    public String generateToken(String username, String role, String publicKey) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String token = jwtBuilder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("username", username)
                .claim("role", role)
                .setSubject("authentication-login")
                .setExpiration(DateUtil.offsetHour(new Date(), 12))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, publicKey)
                .compact();
        return token;
    }

    /**
     * 解密token
     *
     * @return {@link String}
     */
    public Map<String, String> deToken(String token, String publicKey) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(publicKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Map<String, String> map = new HashMap<>(2);
        map.put("username", claims.get("username", String.class));
        return null;
    }
}
