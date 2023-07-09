package com.ipseg.studyTime.security.provider;

import com.ipseg.studyTime.security.utils.JwtUtils;
import com.ipseg.studyTime.security.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
public class JwtTokensProvider {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    public boolean validate(String token) throws IOException {
        log.info("JwtTokensProvider - validate process");

        if(jwtUtils.validate(token)) {
            return true;
        } else {
            return false;
        }
    }

    //인증 객체 생성
    public Authentication getAuthentication(String token) throws IOException {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtUtils.getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
