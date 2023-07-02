package com.ipseg.studyTime.api.user.service;

import com.ipseg.studyTime.api.user.dto.userJoin.UserJoinRequest;
import com.ipseg.studyTime.api.user.dto.userJoin.UserJoinResponse;
import com.ipseg.studyTime.api.user.model.User;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import com.ipseg.studyTime.common.response.BusinessException;
import com.ipseg.studyTime.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public UserJoinResponse joinUser(UserJoinRequest user) {
        String decId = user.getEncId();
        String encPass = user.getEncPass();
        String encPassCheck = user.getEncPassCheck();
        String decName = user.getEncName();
        String hashPass = "";

        if(!encPass.equals(encPassCheck)) {
            throw new BusinessException(ResultCode.ERROR_007);
        }
        UserJoinResponse response = new UserJoinResponse();
        response.setId("");
        response.setName("");
        return response;
    }

}
