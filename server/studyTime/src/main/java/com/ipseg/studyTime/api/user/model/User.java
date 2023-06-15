package com.ipseg.studyTime.api.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String encId;
    private String encPass;
    private String encPassCheck;
    private String encName;
}
