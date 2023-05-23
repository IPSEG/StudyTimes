package com.ipseg.studyTime.framework.config;

//import com.ipseg.studyTime.provider.StudyTimeAuthenticationProvider;
import com.ipseg.studyTime.common.handler.CustomAccessDeniedHandler;
import com.ipseg.studyTime.provider.StudyTimeAuthenticationProvider;
import com.ipseg.studyTime.security.StudyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;


@Configuration("StudyTimesSecurityConfig")
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                .antMatchers("/oauth/**").permitAll();
        http.formLogin();
        http.csrf().disable();
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        return http.build();
    }

    @Bean
    public StudyTimeAuthenticationProvider studyTimeAuthenticationProvider() {
        return new StudyTimeAuthenticationProvider();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(studyTimeAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }
    //TODO : 이 설정법으로 하면 오류나던데 왜 그런지 원인 분석
//        @Bean
//        public AuthenticationManager authManager() throws Exception{
//            AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder();
//            auth.authenticationProvider(
//                    studyTimeAuthenticationProvider());
//
//            return auth.build();
//        }
//
}
