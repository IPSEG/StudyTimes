package com.ipseg.studyTime.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${token.key}")
    private String tokenKey;

    /**
     * jwt 생성 함수
     * @param claims
     * @param type
     * @return
     */
    public String createToken(Claims claims, String type) {
        Date date = new Date();

        if("accessToken".equals(type)) {
            date.setTime(date.getTime() + 18000000);
        } else {
            date.setTime(date.getTime() + 36000000);
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setSubject(type)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, tokenKey)
                .compact();
    }

    /**
     * jwtToken 검증 함수
     * @param token
     * @return
     * @throws IOException
     */
    public boolean validate(String token) throws IOException {
        try{
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(tokenKey)
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();

            int tokenExpireTime = (int) claims.get("exp");
            int currentTime = (int) (System.currentTimeMillis()/1000);

            if(tokenExpireTime > currentTime) {
                return true;
            } else {
                log.info("JwtUtils - validate : Token expired.");
                return false;
            }
        } catch(Exception e) {
            log.info("JwtUtils - validate : validation Failed.");
            return false;
        }
    }

    public String getUserName(String token) {

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(tokenKey)
                .parseClaimsJws(token);

        Claims claims = claimsJws.getBody();

        return String.valueOf(claims.get("userId"));
    }
}
