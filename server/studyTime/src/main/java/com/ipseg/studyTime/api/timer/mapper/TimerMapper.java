package com.ipseg.studyTime.api.timer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Mapper
public interface TimerMapper {
    int timerAdd(HashMap<String, Object> param);
}
