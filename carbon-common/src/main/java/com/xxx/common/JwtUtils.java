package com.xxx.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JwtUtils {
    private static final long EXPIRATION = 60 * 60 * 24 * 7L;
    private static final String SECRET = "MY_SECRET";
    public static String createToken(Map<String,String> claimMap){
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        //设置JWT头部
        HashMap<String, Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        //创建token
        JWTCreator.Builder builder = JWT.create();

        //创建payload
        Set<String> keys = claimMap.keySet();
        for (String key : keys) {
            String value = claimMap.get(key);
            builder.withClaim(key,value);
        }

        return builder.withHeader(map)
                // 设置过期时间
                .withExpiresAt(expiration)
                // 设置签名解码算法
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verifyToken(String token){
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
}
