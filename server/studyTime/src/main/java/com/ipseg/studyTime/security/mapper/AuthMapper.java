package com.ipseg.studyTime.security.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface AuthMapper {
    public HashMap<String, Object> selectUser(String param);
}
