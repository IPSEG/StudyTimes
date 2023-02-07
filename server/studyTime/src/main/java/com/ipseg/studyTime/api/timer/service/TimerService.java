package com.ipseg.studyTime.api.timer.service;

import com.ipseg.studyTime.api.timer.mapper.TimerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class TimerService {

    TimerMapper timerMapper;

    public TimerService(TimerMapper timerMapper) {
        this.timerMapper = timerMapper;
    }

    public ResponseEntity<Object> addTimer(HashMap<String, Object> param) {
        int result = timerMapper.timerAdd(param);
        if(result == 1)
            return new ResponseEntity<Object>(HttpStatus.OK);
        else
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
}
