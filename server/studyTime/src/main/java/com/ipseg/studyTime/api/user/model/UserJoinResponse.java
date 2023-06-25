package com.ipseg.studyTime.api.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserJoinResponse {
    private String id;
    private String name;

    public static UserJoinResponse of(User entity) {
       return UserJoinResponse.builder()
               .id(entity.getEncId())
               .name(entity.getEncName())
               .build();

    }
}
