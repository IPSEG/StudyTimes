package com.ipseg.studyTime.security.filter;

import com.ipseg.studyTime.common.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secretkey}")
     String secretKey;

    @Value("${jwt.secretkey.fileRead:false}")
    private boolean fileRead;

    @Value("jwt.secretkey.fileRead.path")
    private String filePath;

    /**
     * jwtToken 검증 함수
     * @param token
     * @return
     * @throws IOException
     */
    public boolean validate(String token) throws IOException {

        PublicKey publicKey = getPublicKey();

        if(publicKey == null) {
            return false;
        }

        try{
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(publicKey)
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

    /**
     * secretKey(publickey) 생성 함수
     * @return
     * @throws IOException
     */
    private PublicKey getPublicKey() throws IOException {
        byte[] decodedPublicKey;

        log.info("fileReadOption : {}", fileRead);

        if(fileRead) {
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(filePath));
            String publicKeyStr = new String(publicKeyBytes);

            secretKey = publicKeyStr.replace(Constant.PUBLIC_KEY_PREFIX, "")
                    .replace(Constant.PUBLIC_KEY_POSTFIX,"")
                    .replace("\n","");
        }

        log.info("secretKey : {}", secretKey);

        decodedPublicKey = java.util.Base64.getDecoder().decode(secretKey.getBytes());
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedPublicKey);

        KeyFactory keyFactory;
        PublicKey publicKey;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("Failed to create RSA KeyFactory", e);
            return null;
        } catch (InvalidKeySpecException e) {
            log.error("Invalid RSA public key specification", e);
            return null;
        }
        return publicKey;
    }

    public String getUserName(String token) throws IOException {
        PublicKey publicKey = getPublicKey();

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token);

        Claims claims = claimsJws.getBody();

        return claims.getSubject();
    }
}
