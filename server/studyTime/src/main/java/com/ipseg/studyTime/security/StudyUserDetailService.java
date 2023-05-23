package com.ipseg.studyTime.security;

import com.ipseg.studyTime.security.service.StudyPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class StudyUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(StudyUserDetailService.class);

    @Autowired
    private StudyPlatformService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = service.selectUser(username);

        if (user == null) {
            logger.info("Not found user : " + username);
            throw new UsernameNotFoundException("Not found user : " + username);
        } else {

            UserDetails userDetails = user.toUserDetails();

            return userDetails;
        }
    }
}
