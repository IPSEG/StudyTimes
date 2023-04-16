//package com.ipseg.studyTime.provider;
//
//import com.ipseg.studyTime.common.client.Client;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class StudyTimeAuthenticationProvider implements AuthenticationProvider {
//
//    private ProviderUtils providerUtils = new ProviderUtils();
//    private ProviderService providerService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        String grantType = request.getParameter("grant_type");
//        String basicToken = request.getParameter("Authorization");
//        String userName = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        boolean isAuthenticated = false;
//
//        String clientId = null;
//        if(basicToken != null && basicToken.startsWith("Basic")) {
//            clientId = providerUtils.getClientIdFromSecurityContext();
//        }
//
//        Client client = providerService.selectClient(clientId);
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
