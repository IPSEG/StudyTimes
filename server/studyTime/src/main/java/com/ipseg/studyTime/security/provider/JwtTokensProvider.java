package com.ipseg.studyTime.security.provider;

import com.ipseg.studyTime.security.utils.JwtUtils;
import com.ipseg.studyTime.security.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
public class JwtTokensProvider {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    //인증 객체 생성
    public Authentication authenticate(String token) throws IOException {
        if(!jwtUtils.validate(token)) {
            throw new BadJwtException("Token Not Valid");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtUtils.getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
