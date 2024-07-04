package com.example.springcloudapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import java.util.Calendar;
import java.util.Map;

/**
 * jwt util class to create the token and verify the token
 */
public class JWTUtil {
    private static String SECRET = "privatekey#^&^%!save";

    /**
     * pass the information in payload to get token
     * @param map payload
     * @return token
     */
    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach(builder::withClaim);

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 3600); //默认1h过期

        builder.withExpiresAt(instance.getTime());//指定令牌的过期时间
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * Verify the token
     * @param token token string
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * acquire the payload from token
     */
    public static Map<String, Claim> getPayloadFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaims();
    }
}
