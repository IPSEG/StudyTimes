package com.ipseg.studyTime.security.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface CustomAuthMapper {
    HashMap<String, Object> selectUser(String username);
}
