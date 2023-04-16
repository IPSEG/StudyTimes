//package com.ipseg.studyTime.provider;
//
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class ProviderUtils {
//
//    public String getClientIdFromSecurityContext() {
//        String clientId = null;
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        if(securityContext.getAuthentication() != null) {
//            UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
//            clientId = userDetails.getUsername();
//        }
//        return clientId;
//    }
//}
