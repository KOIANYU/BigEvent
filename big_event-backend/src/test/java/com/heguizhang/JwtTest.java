package com.heguizhang;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        // 生成jwt
        String token = JWT.create()
                .withClaim("user", claims) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 添加过期时间
                .sign(Algorithm.HMAC256("KOIAN"));
        System.out.println(token);
    }

    @Test
    public void testParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MzE4MTQwMzl9" +
                ".3xTgtuqIpBNK1qViL9NP59Qsdo9d77iq4Ruq2BvbhgE";

        JWTVerifier jwtverifier = JWT.require(Algorithm.HMAC256("KOIAN")).build();

        DecodedJWT decodeJWT = jwtverifier.verify(token); // 解析token, 生成一个解析后的jwt对象

        Map<String, Claim> claims = decodeJWT.getClaims();

        System.out.println(claims.get("user"));
    }

}
