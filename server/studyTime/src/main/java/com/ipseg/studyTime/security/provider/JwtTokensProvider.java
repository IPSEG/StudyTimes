package com.ipseg.studyTime.security.provider;

import com.ipseg.studyTime.security.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class JwtTokensProvider {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Value("${jwt.secretkey.fileRead:false}")
    private boolean fileRead;

    //토큰 검증 로직
    public boolean validate(String token) throws IOException {
        log.info("JwtTokensProvider validate process");

        PublicKey publicKey = getSecretKey();

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();
            int currentTimeSeconds = (int) (System.currentTimeMillis()/1000);

            if((int) claims.get("exp") > currentTimeSeconds) {
                return true;
            } else {
                log.info("JwtTokensProvider - validate : Token expired.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid Token Info.");
            return false;
        }
    }

    //인증 객체 생성
    public Authentication getAuthentication(String token) throws IOException {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserName(String token) throws IOException {
        PublicKey publicKey = getSecretKey();

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token);

        Claims claims = claimsJws.getBody();

        return claims.getSubject();
    }

    public PublicKey getSecretKey() throws IOException {
        byte[] decodedPublicKeyBytes;

        if(fileRead) {
            String publicKeyFilePath = "/Users/yangseungbin/Documents/public_key.pem";
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyFilePath));
            String publicKeyStr = new String(publicKeyBytes);

            publicKeyStr = publicKeyStr.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----","").replace("\n","");

            decodedPublicKeyBytes = Base64.getDecoder().decode(publicKeyStr.getBytes());
        } else {
            decodedPublicKeyBytes = Base64.getDecoder().decode(secretKey.getBytes());
        }

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedPublicKeyBytes);

        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to create RSA KeyFactory", e);
        }

        PublicKey publicKey;
        try {
            publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("Invalid RSA public key specification", e);
        }

        return publicKey;
    }

}
