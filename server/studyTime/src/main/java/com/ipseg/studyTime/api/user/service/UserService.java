package com.ipseg.studyTime.api.user.service;

import com.ipseg.studyTime.api.user.model.User;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public ResponseEntity<Object> joinUser(User user) {
        String decId = "";
        String decPw = "";
        String decPwRe = "";
        String decName = "";
        String decEmail = "";
        String decSecreatKey = "";

        try {

        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }

        if(!decPw.equals(decPwRe)) {
            return ApiResultEntity.fail(ResultCode.ERROR_007);
        }

        if(decId.length() > 10) {
            return ApiResultEntity.fail(ResultCode.ERROR_008);
        }
    }

}
