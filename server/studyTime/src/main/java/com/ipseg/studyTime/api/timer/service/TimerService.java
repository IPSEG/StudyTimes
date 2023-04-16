package com.ipseg.studyTime.api.timer.service;

import com.ipseg.studyTime.api.timer.mapper.TimerMapper;
import com.ipseg.studyTime.api.timer.model.Timer;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import lombok.extern.slf4j.Slf4j;
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
        dbMap.put("days", timer.getDays());
        dbMap.put("hours", timer.getHours());
        dbMap.put("minutes", timer.getMinutes());
        dbMap.put("seconds", timer.getSeconds());
        dbMap.put("userSeq", timer.getUserSeq());
        timerMapper.addTimer(dbMap);

        return ApiResultEntity.success();
    }

    public ResponseEntity<Object> getUserTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("userSeq", timer.getUserSeq());

        List<HashMap<String, Object>> timerList = timerMapper.getUserTimer(dbMap);

        return ApiResultEntity.success(timerList);
    }

    public ResponseEntity<Object> modifyTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("userSeq", timer.getUserSeq());
        dbMap.put("timerSeq", timer.getTimerSeq());
        dbMap.put("days", timer.getDays());
        dbMap.put("hours", timer.getHours());
        dbMap.put("minutes", timer.getMinutes());
        dbMap.put("seconds", timer.getSeconds());
        int result = timerMapper.modifyTimer(dbMap);

        return ApiResultEntity.success(result);
    }

    public ResponseEntity<Object> deleteTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", timer.getTimerSeq());

        if(timer.getTimerSeq() == null) {
            return ApiResultEntity.fail(ResultCode.ERROR_006);
        }

        int result = timerMapper.deleteTimer(dbMap);

        return ApiResultEntity.success(result);
    }
}
