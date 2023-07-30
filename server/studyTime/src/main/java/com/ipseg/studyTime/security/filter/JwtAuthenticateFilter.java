package com.ipseg.studyTime.security.filter;

import com.ipseg.studyTime.security.provider.JwtTokensProvider;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token;

        log.info("JwtAuthenticateFilter called. Authorization Info : {}", authorization);

        if(authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            try {
                if(!StringUtils.isBlank(token) && jwtTokensProvider.validate(token)) {
                    log.info("JwtAuthenticateFilter Token validate success");
                    Authentication auth = jwtTokensProvider.getAuthentication(token);    // 인증 객체 생성
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new BadJwtException("Token Not Valid");
            }
            filterChain.doFilter(request, response);
        } else {
        }
    }
}
