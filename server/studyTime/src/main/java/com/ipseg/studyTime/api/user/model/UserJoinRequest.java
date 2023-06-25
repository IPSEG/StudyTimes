package com.ipseg.studyTime.api.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRequest {
    private String encId;
    private String encPass;
    private String encPassCheck;
    private String encName;

    public static User toEntity(UserJoinRequest dto) {
        return User.builder()
                .encId(dto.encId)
                .encPass(dto.encPass)
                .encPassCheck(dto.encPassCheck)
                .encName(dto.encName)
                .build();
    }
}
