package com.ipseg.studyTime.common.utils;

import com.ipseg.studyTime.common.Constant;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.security.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class SecurityUtils {

    @Value("${jwt.rsa.privatekey}")
    private String privateKey;

    @Value("${aes.key}")
    private String aesKey;

    public String getSalt() throws NoSuchAlgorithmException {
        //SecureRandom 객체 생성
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        //난수 바이트 생성
        byte[] salt = new byte[20];
        random.nextBytes(salt);

        //10진수 문자열로 변경
        StringBuilder sb = new StringBuilder();
        for (byte b : salt) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String getHash(String salt, String pwd) throws NoSuchAlgorithmException {
        byte[] saltByte = salt.getBytes();
        byte[] pwdByte = pwd.getBytes();
        byte[] newBytes = new byte[saltByte.length + pwdByte.length];

        System.arraycopy(pwdByte, 0, newBytes, 0, pwdByte.length);
        System.arraycopy(saltByte, 0, newBytes, pwdByte.length, saltByte.length);

        //SHA-256으로 해쉬를하기 위한 MessageDigest 생성
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        //MessageDigest객체내부 digest값에 newBytes값을 업데이트
        md.update(newBytes);

        //해시값 생성
        byte[] hashedBytes = md.digest();

        StringBuffer sb = new StringBuffer();

        //해쉬값 byte를 16진수 2자리로 변경하여 StringBuffer에 append
        for(byte value : hashedBytes) {
            sb.append(String.format("%02x", value));
        }

        return sb.toString();
    }

    public String generateHash(String key, String bodyJsonString, String salt) {
        String HASH = "";
        try {
            String data = bodyJsonString + salt;

            // calculate Hmac
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            // byte array to hex
            byte[] byteArray = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(byteArray.length * 2);
            for (byte b : byteArray)
                sb.append(String.format("%02x", b));
            HASH = sb.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error(e.getMessage(), e);
        }
        return HASH.toUpperCase();
    }

    /**
     * rsa 복호화
     * @param encrypted
     * @return
     * @throws Exception
     */
    public String decrypt(String encrypted) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey.getBytes());

        //Base64 인코딩되어 전달된 암호화된 값 디코딩
        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);

        //privateKeySpec생성
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        //RSA KeyFactory생성
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        //PrivateKey 객체생성
        PrivateKey privateKeys = keyFactory.generatePrivate(privateKeySpec);

        //Cipher 생성/설정/복호화
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKeys);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes);
    }

    /**
     * AES 암호화
     * @param plain
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String aesEncrypt(byte[] plain) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKey key = new SecretKeySpec(aesKey.getBytes(), "AES");

        Cipher cipher =  Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes = cipher.doFinal(plain);
        String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);

        return encrypted;
    }

    /**
     * AES 복호화
     * @param plain
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String aesDecrypt(String plain) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKey key = new SecretKeySpec(aesKey.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] encryptedBytes = Base64.getDecoder().decode(plain);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes);
    }

    /**
     * 패스워드 유효성 검사 함수
     * @param id
     * @param pw
     * @return
     */
    public HashMap<String, String> checkPassword(String id, String pw) {
        HashMap<String, String> result = new HashMap<>();
        String resultCd = ResultCode.SUCCESS.code();
        String resultMsg = ResultCode.SUCCESS.msg();
        int pwLen = pw.length();

        if(pw.contains(id)) {
            resultCd = ResultCode.ERROR_008.code();
            resultMsg = "ID는 비밀번호로 사용불가 합니다.";
        }

        if (pw.contains(" ")) {
            resultCd = ResultCode.ERROR_008.code();
            resultMsg = "비밀번호에 공백이 포함되어있습니다.";
        }

        if(!ResultCode.SUCCESS.equals(resultCd)) {
            result.put("resultCd", resultCd);
            result.put("resultMsg", resultMsg);
            return result;
        }

        String complexPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$"; // 영문, 숫자, 특수문자 정규식
        String repeatedPattern = "(\\w)\\1\\1\\1"; // 반복 문자 정규식

        Matcher complexMatcher = Pattern.compile(complexPattern).matcher(pw);
        Matcher repeatedMatcher = Pattern.compile(repeatedPattern).matcher(pw);

        boolean isComplexPass = complexMatcher.find();
        boolean isRepeated = repeatedMatcher.find();

        if(isRepeated) {
            resultCd = ResultCode.ERROR_008.code();
            resultMsg = "동일한 문자,숫자는 연속해서 사용불가합니다.";
        } else if(!isComplexPass) {
            resultCd = ResultCode.ERROR_008.code();
            resultMsg = "비밀번호는 영문,숫자,특수문자를 조합해야 합니다.";
        } else if(pwLen < 8) {
            resultCd = ResultCode.ERROR_008.code();
            resultMsg = "비밀번호는 8자 이상이어야 합니다.";
        } else if(pwLen > 16) {
            resultCd = ResultCode.ERROR_008.code();
            resultMsg = "비밀번호는 16자 이하여야 합니다.";
        }

        result.put("resultCd", resultCd);
        result.put("resultMsg", resultMsg);
        return result;
    }
}
