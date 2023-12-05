package com.ipseg.studyTime.security.filter;

import com.ipseg.studyTime.common.Constant;
import com.ipseg.studyTime.security.provider.JwtTokensProvider;
import com.ipseg.studyTime.security.utils.JwtUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokensProvider jwtTokensProvider;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token;
        String path = request.getServletPath();

        log.info("JwtAuthenticateFilter - Authorization Info : {}", authorization);

        //로그인, 회원가입 관련 API가 아니면 토큰 검증
        if(!Constant.AUTH_URL.equals(path)) {
            if (authorization != null && authorization.startsWith("Bearer")) {
                token = authorization.substring(7);

                try {
                    Authentication authentication = jwtTokensProvider.authenticate(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BadJwtException("Token Not Valid");
                }
                filterChain.doFilter(request, response);
            } else {
                throw new BadJwtException("Token Not Valid");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
