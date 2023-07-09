package com.ipseg.studyTime.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HashMap<String, Object> result = authService.selectUser(username);

        //user 정보 추출
        String userId = String.valueOf(result.get("userId"));
        String userType = String.valueOf(result.get("userType"));

        //user 권한 설정
        List<GrantedAuthority> userRoles = new ArrayList<>();
        if("admin".equals(userType)) {
            userRoles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            userRoles.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if("user".equals(userType)) {
            userRoles.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        //userDetails 정보 생성
        CustomUserDetails customUserDetails =  new CustomUserDetails(userId, "noneUse", userRoles);

        return customUserDetails;
    }
}
