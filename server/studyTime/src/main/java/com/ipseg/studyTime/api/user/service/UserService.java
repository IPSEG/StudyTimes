package com.ipseg.studyTime.api.user.service;

import com.ipseg.studyTime.api.user.dto.userJoin.UserJoinRequest;
import com.ipseg.studyTime.api.user.dto.userJoin.UserJoinResponse;
import com.ipseg.studyTime.api.user.dto.userJoin.UserLoginRequest;
import com.ipseg.studyTime.api.user.mapper.UserMapper;
import com.ipseg.studyTime.common.Constant;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResult;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import com.ipseg.studyTime.common.utils.SecurityUtils;
import com.ipseg.studyTime.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService {
    private SecurityUtils securityUtils;
    private UserMapper userMapper;
    private JwtUtils jwtUtils;

    @Value("${run.environment}")
    private String runEnv;

    public UserService(SecurityUtils securityUtils, UserMapper userMapper, JwtUtils jwtUtils) {
        this.securityUtils = securityUtils;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public ResponseEntity<ApiResult<Object>> joinUser(UserJoinRequest userJoinRequest) {
        String encId = userJoinRequest.getEncId();
        String encPass = userJoinRequest.getEncPass();
        String encPassCheck = userJoinRequest.getEncPassCheck();
        String encName = userJoinRequest.getEncName();
        String decId = "";
        String decPass = "";
        String decPassCheck = "";
        String decName = "";
        String decSalt = "";
        String hashedPass = "";
        String encSalt = "";

        //1. 파라미터 복호화
        if(!Constant.RUN_ENVIRONMENT_LOCAL.equals(runEnv)) {
            try {
                decId = securityUtils.decrypt(encId);
                decPass = securityUtils.decrypt(encPass);
                decPassCheck = securityUtils.decrypt(encPassCheck);
                decName = securityUtils.decrypt(encName);
            } catch (Exception e) {
                log.error("Decrypt Error : {}", e.getStackTrace()[0]);
            }
        }

        //2. id 유효성 체크
        if(decId.length() > 20) {
            return ApiResultEntity.fail(ResultCode.ERROR_009);
        }

        boolean isValidId = Pattern.compile("^[a-zA-Z0-9]{4,20}$").matcher(decId).find();

        if(!isValidId) {
            return ApiResultEntity.fail(ResultCode.ERROR_011);
        }

        //3. 이름 유효성 체크
        boolean isValidName = Pattern.compile("^[a-zA-Z0-9가-힣]*$").matcher(decName).find();
        if(!isValidName) {
            return ApiResultEntity.fail(ResultCode.ERROR_013);
        }

        //4. 패스워드 유효성 체크
        if(!decPass.equals(decPassCheck)) {
            return ApiResultEntity.fail(ResultCode.ERROR_007);
        }

        HashMap<String, String> result = securityUtils.checkPassword(decId, decPass);

        if(ResultCode.SUCCESS.code().equals(result.get("resultCd"))) {
            try {
                decSalt = securityUtils.getSalt();
                hashedPass = securityUtils.getHash(decSalt, decPass);
                encSalt = securityUtils.aesEncrypt(decSalt.getBytes());
            } catch(NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }

        //5. 동일한 id 존재여부 확인
        int count =  userMapper.findById(decId);
        if(count > 0) {
            return ApiResultEntity.fail(ResultCode.ERROR_012);
        }

        Map<String, String> userMap = new HashMap<>();
        Map<String, String> saltMap = new HashMap<>();

        userMap.put("userId", decId);
        userMap.put("userPass", hashedPass);
        userMap.put("userName", decName);
        userMap.put("userType", "ROLE_USER");

        saltMap.put("userId", decId);
        saltMap.put("userSalt", encSalt);

        userMapper.setUser(userMap);
        userMapper.setSalt(saltMap);

        UserJoinResponse response = new UserJoinResponse();
        response.setId(decId);
        return ApiResultEntity.success();
    }

    public ResponseEntity<ApiResult<Object>> loginUser(UserLoginRequest userLoginRequest) {
        String encId = userLoginRequest.getEncId();
        String encPass = userLoginRequest.getEncPass();
        String decId = encId;
        String decPass = encPass;

        // 1.요청 파라미터 복호화
        if(!Constant.RUN_ENVIRONMENT_LOCAL.equals(runEnv)) {
            try {
                decId = securityUtils.decrypt(encId);
                decPass = securityUtils.decrypt(encPass);
            } catch (Exception e) {
                log.error("Decrypt Error : {}", e.getStackTrace()[0]);
            }
        }

        // 2.salt 조회 및 패스워드 해싱
        String encSalt = userMapper.getSalt(decId);
        String decSalt = "";
        String shaPass = "";

        try {
            decSalt = securityUtils.aesDecrypt(encSalt);
            shaPass = securityUtils.getHash(decSalt, decPass);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResultEntity.fail();
        }

        // 3.패스워드 검증
        Map<String, Object> user = userMapper.selectUserByUserId(decId);
        String savedPass = String.valueOf(user.get("userPass"));

        if(!shaPass.equals(savedPass)) {
            return ApiResultEntity.fail(ResultCode.ERROR_004);
        }

        // 4.토큰 생성
        Claims claims = Jwts.claims();
        claims.put("userId", decId);

        String accessToken = jwtUtils.createToken(claims, "accessToken");
        String refreshToken = jwtUtils.createToken(claims, "refreshToken");

        try {
            if(jwtUtils.validate(accessToken)) {
                log.info("no problem");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("refreshToken", refreshToken);

        return ApiResultEntity.success(result);
    }
}
