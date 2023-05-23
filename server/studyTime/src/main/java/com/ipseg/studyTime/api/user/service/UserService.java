package com.ipseg.studyTime.api.user.service;

import com.ipseg.studyTime.api.user.model.User;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import com.ipseg.studyTime.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public ResponseEntity<Object> joinUser(User user) {
        String decId = user.getEncId();
        String encPass = user.getEncPass();
        String encPassCheck = user.getEncPassCheck();
        String decName = user.getEncName();
        String hashPass = "";

        if(!encPass.equals(encPassCheck)) {
            return ApiResultEntity.fail(ResultCode.ERROR_007);
        }

        if(decId.length() > 10) {
            return ApiResultEntity.fail(ResultCode.ERROR_008);
        }

        try {
            hashPass = SecurityUtils.hashPassword(encPass, "temporarySalt");
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }

        return ApiResultEntity.success();
    }

}
