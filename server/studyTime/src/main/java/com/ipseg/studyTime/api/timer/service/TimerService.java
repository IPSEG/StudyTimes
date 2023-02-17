package com.ipseg.studyTime.api.timer.service;

import com.ipseg.studyTime.api.timer.mapper.TimerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class TimerService {

    TimerMapper timerMapper;

    public TimerService(TimerMapper timerMapper) {
        this.timerMapper = timerMapper;
    }

    public ResponseEntity<Object> addTimer(HashMap<String, Object> param) {
        log.info("TimerService - addTimer : {}", param);
        int result = timerMapper.timerAdd(param);
        log.info("TimerService - addTimer complete: {}", param);

        if(result == 1)
            return new ResponseEntity<Object>(HttpStatus.OK);
        else
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> getUserTimer(HashMap<String, Object> param) {
        log.info("TimerService - getUserTimer : {}", param);
        List<HashMap<String, Object>> timerList = timerMapper.getUserTimer(param);
        log.info("TimerService - getUserTimer complete : {}", param);

        if(timerList.size() > 1) {
            return new ResponseEntity<Object>(timerList, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(timerList, HttpStatus.OK);
        }
    }
}
