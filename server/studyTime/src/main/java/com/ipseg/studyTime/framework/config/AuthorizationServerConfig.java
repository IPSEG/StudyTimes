//package com.ipseg.studyTime.framework.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.HttpEntity;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.security.KeyPair;
//import java.util.UUID;
//
//@Configuration
//@EnableWebSecurity
//public class AuthorizationServerConfig {
////    @Bean
////    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception{
////
////    }
//
//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient registeredClient =
//                RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("testId")
//                .clientSecret("testSecret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
//                .scope(OidcScopes.OPENID)
//                .build();
//        return new InMemoryRegisteredClientRepository(registeredClient);
//    }
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
//                new OAuth2AuthorizationServerConfigurer();
//        http.apply(authorizationServerConfigurer);
//        return http.formLogin(Customizer.withDefaults()).build();
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//
//        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//
//        for (JwtAccessTokenConverterConfigurer configurer : configurers) {
//            configurer.configure(converter);
//        }
//        KeyPair keyPair = new KeyStoreKeyFactory(
//                new ClassPathResource(privateKeyFile), privateKeyPassword.toCharArray())
//                .getKeyPair(privateKeyAlias, privateKeyPassword.toCharArray());
//        converter.setKeyPair(keyPair);
//
//        return converter;
//    }
//
//    @Bean
//    public JwtTokenStore tokenStore() {
//        JwtTokenStore store = new JwtTokenStore(jwtAccessTokenConverter());
//        return store;
//    }
//
//}
