package com.ipseg.studyTime.security.service;

import com.ipseg.studyTime.security.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private AuthMapper authMapper;
    
    public HashMap<String, Object> selectUser(String username) {
        HashMap<String, Object> result = authMapper.selectUser(username);
        return result;
    }
}
