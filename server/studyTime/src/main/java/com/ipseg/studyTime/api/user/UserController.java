package com.ipseg.studyTime.api.user;

import com.ipseg.studyTime.api.user.model.User;
import com.ipseg.studyTime.api.user.dto.userJoin.UserJoinRequest;
import com.ipseg.studyTime.api.user.dto.userJoin.UserJoinResponse;
import com.ipseg.studyTime.api.user.service.UserService;
import com.ipseg.studyTime.common.response.ApiResult;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResult<UserJoinResponse>> joinUser(@RequestBody UserJoinRequest body) {
        ResponseEntity<ApiResult<UserJoinResponse>> success = ApiResultEntity.success(this.userService.joinUser(body));
        return success;
    }
}
