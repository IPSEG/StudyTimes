package com.ipseg.studyTime.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class SecurityUtils {

    @Value("${rsa.privateKey}")
    private static String privateKey;

    public String decryptValue(String value) {
        return "";
    }

    /**
     * 해시 함수
     * @param decPass
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String hashPassword(String decPass, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] byteSalt = salt.getBytes();
        byte[] bytePass = decPass.getBytes();
        byte[] byteSum = new byte[byteSalt.length + bytePass.length];

        //보안 강화를 위해 byte sum
        System.arraycopy(bytePass, 0, byteSum, 0, bytePass.length);
        System.arraycopy(byteSalt, 0, byteSum, 0, byteSalt.length);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(byteSum);

        byte[] byteResult = digest.digest();
        StringBuffer buffer = new StringBuffer();

        for (byte value : byteResult) {
            buffer.append(Integer.toString((value & 0xFF), 16).substring(1));
        }
        return buffer.toString();
    }

    /**
     * rsa 복호화 함수
     * @param value
     * @return
     * @throws NoSuchAlgorithmException,InvalidKeySpecException,NoSuchPaddingException,InvalidKeyException
     * @throws IllegalBlockSizeException,BadPaddingException
     */
    public static String rsaDecrypt(String value) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedValue = Base64.decodeBase64(value);
        byte[] privateKeyDecode = Base64.decodeBase64(privateKey.getBytes());

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyDecode);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedVal = cipher.doFinal(encryptedValue);

        return new String(decryptedVal);
    }
}

