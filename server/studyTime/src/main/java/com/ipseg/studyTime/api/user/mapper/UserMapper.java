package com.ipseg.studyTime.api.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface UserMapper {
    public int findById(String id);
    public String selectById(String id);
    public int setUser(Map<String, String> param);
    public int setSalt(Map<String, String> param);
    public String getSalt(String id);
    HashMap<String, Object> selectUserByUserId(String param);
}
