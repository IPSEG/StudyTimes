package com.ipseg.studyTime.api.user.dto.userJoin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserLoginRequest {
    @NotEmpty
    private String encId;
    @NotEmpty
    private String encPass;
}
