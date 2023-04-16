package com.ipseg.studyTime.api.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String encId;
    private String encPw;
    private String encPwRe;
    private String encName;
    private String encEmail;
    private String encSecreatKey;
}
