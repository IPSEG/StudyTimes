package com.ipseg.studyTime.api.timer.service;

import com.ipseg.studyTime.api.timer.mapper.TimerMapper;
import com.ipseg.studyTime.api.timer.model.Timer;
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

    public ResponseEntity<Object> addTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("hour", timer.getHour());
        dbMap.put("minute", timer.getMinute());
        dbMap.put("seconds", timer.getSeconds());
        dbMap.put("userKey", timer.getUserSeq());

        int result = timerMapper.timerAdd(dbMap);

        if(result == 1)
            return new ResponseEntity<Object>(HttpStatus.OK);
        else
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> getUserTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("userKey", timer.getUserSeq());

        List<HashMap<String, Object>> timerList = timerMapper.getUserTimer(dbMap);

        if(timerList.size() > 1) {
            return new ResponseEntity<Object>(timerList, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(timerList, HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> modifyTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("hour", timer.getHour());
        dbMap.put("minute", timer.getMinute());
        dbMap.put("seconds", timer.getSeconds());

        timerMapper.modifyTimer(dbMap);
        return new ResponseEntity<>("1", HttpStatus.OK);
    }
}
