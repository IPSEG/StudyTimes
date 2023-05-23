package com.ipseg.studyTime.api.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {
    public int joinUser(HashMap<String, String> param);
}
