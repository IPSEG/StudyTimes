package com.ipseg.studyTime.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

public class JwtUtil {

    private static RSAKey generateRsa() throws NoSuchAlgorithmException {
        KeyPair keyPair = generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public Claims getAllClaims(String token) throws NoSuchAlgorithmException {
        System.out.println("getAllClaims token = " + token);
        try {
            return Jwts.parser()
                    .setSigningKey(Base64.decodeBase64(generateRsa().toKeyPair().getPrivate().getEncoded()))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }
}
