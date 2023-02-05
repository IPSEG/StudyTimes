package com.ipseg.studyTime.api.timer.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class TimerService {

    public ResponseEntity<Object> addTimer(HashMap<String, Object> param) {
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
