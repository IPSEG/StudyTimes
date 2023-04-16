package com.ipseg.studyTime.api.user;

import com.ipseg.studyTime.api.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping(value = "/join")
    public ResponseEntity<Object> joinUser(User user) {
        return new ResponseEntity<>();
    }
}
