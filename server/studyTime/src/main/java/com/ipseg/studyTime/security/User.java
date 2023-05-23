package com.ipseg.studyTime.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userId;
    private String userPass;
    private String userName;

    public StudyUserDetails toUserDetails(){
        StudyUserDetails studyUserDetails = new StudyUserDetails();
        studyUserDetails.setUsername(this.userName);
        studyUserDetails.setPassword(this.userPass);
        return studyUserDetails;
    }
}
