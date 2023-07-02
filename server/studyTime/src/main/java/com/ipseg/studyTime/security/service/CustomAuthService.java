package com.ipseg.studyTime.security.service;

import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class CustomAuthService {

    private CustomAuthMapper customAuthMapper;

    public CustomAuthService(CustomAuthMapper customAuthMapper) {
        this.customAuthMapper = customAuthMapper;
    }

    public HashMap<String, Object> selectUser(String username) {
        HashMap<String, Object> result = customAuthMapper.selectUser(username);

        return result;
    }
}
