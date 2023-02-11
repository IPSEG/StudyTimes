package com.ipseg.studyTime.api.timer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface TimerMapper {
    int timerAdd(HashMap<String, Object> param);
    List<HashMap<String, Object>> getUserTimer(HashMap<String, Object> param);
}
