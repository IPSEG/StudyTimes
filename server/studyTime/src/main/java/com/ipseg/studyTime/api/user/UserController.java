package com.ipseg.studyTime.api.user;

import com.ipseg.studyTime.api.user.dto.userJoin.UserJoinRequest;
import com.ipseg.studyTime.api.user.dto.userJoin.UserLoginRequest;
import com.ipseg.studyTime.api.user.service.UserService;
import com.ipseg.studyTime.common.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<ApiResult<Object>> joinUser(@RequestBody UserJoinRequest userJoinRequest) {
        return userService.joinUser(userJoinRequest);
    }

    @PostMapping
    public ResponseEntity<ApiResult<Object>> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.loginUser(userLoginRequest);
    }
}
