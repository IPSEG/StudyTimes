package com.ipseg.studyTime.repository.timer.mybatis;

import com.ipseg.studyTime.api.timer.mapper.TimerMapper;
import com.ipseg.studyTime.repository.timer.TimerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class MyBatisTimerRepositoryImpl implements TimerRepository {

    private final TimerMapper timerMapper;

    @Override
    public int addTimer(HashMap<String, Object> param) {
        return timerMapper.addTimer(param);
    }

    @Override
    public int modifyTimer(HashMap<String, Object> param) {
        return timerMapper.modifyTimer(param);
    }

    @Override
    public List<HashMap<String, Object>> getUserTimer(HashMap<String, Object> param) {
        return timerMapper.getUserTimer(param);
    }

    @Override
    public int deleteTimer(HashMap<String, Object> param) {
        return timerMapper.deleteTimer(param);
    }
}
