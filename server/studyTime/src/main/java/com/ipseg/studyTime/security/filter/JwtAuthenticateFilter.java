package com.ipseg.studyTime.security.filter;

import com.ipseg.studyTime.security.JwtUtil;
import com.ipseg.studyTime.security.provider.JwtTokensProvider;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil = new JwtUtil();
    private JwtTokensProvider jwtTokensProvider = new JwtTokensProvider();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String username = "";
        String token = "";

        if(authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            try {
                if(StringUtils.isBlank(token) && jwtTokensProvider.validate(token)) {
                    Authentication auth = jwtTokensProvider.getAuthentication(token);    // 인증 객체 생성
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
