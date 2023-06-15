package com.ipseg.studyTime.security.filter;

import com.ipseg.studyTime.security.JwtUtil;
import io.jsonwebtoken.Claims;
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
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String username = "";
        String token = "";

        if(authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            try {
                Claims claims = jwtUtil.getAllClaims(token);
                System.out.println(claims);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
}
