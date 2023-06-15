package com.ipseg.studyTime.api.user;

import com.ipseg.studyTime.api.user.model.User;
import com.ipseg.studyTime.api.user.service.UserService;
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

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> joinUser(@RequestBody User user) {
        log.info("testeste");
        return this.userService.joinUser(user);
    }
}
