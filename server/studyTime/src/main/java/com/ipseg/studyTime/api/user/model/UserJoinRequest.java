package com.ipseg.studyTime.api.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserJoinRequest {
    @NotEmpty
    @Size(max = 10, message = "아이디는 10자 이하여야 합니다.")
    private String encId;
    @NotEmpty
    private String encPass;
    @NotEmpty
    private String encPassCheck;
    @NotEmpty
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
