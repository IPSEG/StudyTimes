package com.ipseg.studyTime.api.timer.service;

import com.ipseg.studyTime.api.timer.mapper.TimerMapper;
import com.ipseg.studyTime.api.timer.model.Timer;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import com.ipseg.studyTime.common.response.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TimerService {

    TimerMapper timerMapper;

    public TimerService(TimerMapper timerMapper) {
        this.timerMapper = timerMapper;
    }

    public Timer addTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("days", timer.getDays());
        dbMap.put("hours", timer.getHours());
        dbMap.put("minutes", timer.getMinutes());
        dbMap.put("seconds", timer.getSeconds());
        dbMap.put("userSeq", timer.getUserSeq());
        int result = timerMapper.addTimer(dbMap);
        if (result <= 0) {
            throw new BusinessException(ResultCode.ERROR_001);
        }

        return timer;
    }

    public List<Timer> getUserTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("userSeq", timer.getUserSeq());

        List<HashMap<String, Object>> timerList = timerMapper.getUserTimer(dbMap);

        return timerList.stream()
                .map(row -> Timer.builder()
                        .timerSeq((Integer) row.get("TIMER_SEQ"))
                        .userSeq((Integer) row.get("USER_SEQ"))
                        .days((int) row.get("DAYS"))
                        .hours((int) row.get("HOURS"))
                        .minutes((int) row.get("MINUTES"))
                        .seconds((int) row.get("SECONDS"))
                        .createDate((String) row.get("CREATE_DATE"))
                        .updateDate((String) row.get("UPDATE_DATE"))
                        .build())
                .collect(Collectors.toList());
    }

    public Timer modifyTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("userSeq", timer.getUserSeq());
        dbMap.put("timerSeq", timer.getTimerSeq());
        dbMap.put("days", timer.getDays());
        dbMap.put("hours", timer.getHours());
        dbMap.put("minutes", timer.getMinutes());
        dbMap.put("seconds", timer.getSeconds());
        int result = timerMapper.modifyTimer(dbMap);

        if (result <= 0) {
            throw new BusinessException(ResultCode.ERROR_001);
        }

        return timer;
    }

    public boolean deleteTimer(Timer timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", timer.getTimerSeq());

        if(timer.getTimerSeq() == null) {
            throw new BusinessException(ResultCode.ERROR_006);
        }

        int result = timerMapper.deleteTimer(dbMap);
        return result > 0;
    }
}
