package com.task.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey="jotang";//设置JWT密钥
    private static Long expire=3600000L;;//设置JWT令牌有效时间为一小时

    /*
    生成JWT令牌
     */
    public static String generateJwt(Map<String,Object> claims){
        String jwt= Jwts.builder()
                .addClaims(claims)//封装自定义内容
                .signWith(SignatureAlgorithm.HS256,signKey)//封装签名算法和密钥
                .setExpiration(new Date(System.currentTimeMillis()+expire))//封装有效时间
                .compact();
        return jwt;
    }


    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)//设置解析的JWT的密钥
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
