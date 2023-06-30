package com.ipseg.studyTime.api.timer.service;

import com.ipseg.studyTime.api.timer.dto.TimerQuery.TimerQueryRequest;
import com.ipseg.studyTime.api.timer.dto.TimerDto;
import com.ipseg.studyTime.api.timer.dto.TimerQuery.TimerQueryResponse;
import com.ipseg.studyTime.api.timer.dto.timerCreate.TimerCreateRequest;
import com.ipseg.studyTime.api.timer.dto.timerCreate.TimerCreateResponse;
import com.ipseg.studyTime.api.timer.dto.timerDelete.TimerDeleteRequest;
import com.ipseg.studyTime.api.timer.dto.timerDelete.TimerDeleteResponse;
import com.ipseg.studyTime.api.timer.dto.timerModify.TimerModifyRequest;
import com.ipseg.studyTime.api.timer.dto.timerModify.TimerModifyResponse;
import com.ipseg.studyTime.api.timer.mapper.TimerMapper;
import com.ipseg.studyTime.api.timer.model.Timer;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public TimerCreateResponse addTimer(TimerCreateRequest timerInformation) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("days", timerInformation.getDays());
        dbMap.put("hours", timerInformation.getHours());
        dbMap.put("minutes", timerInformation.getMinutes());
        dbMap.put("seconds", timerInformation.getSeconds());
        dbMap.put("userSeq", timerInformation.getUserSeq());
        int result = timerMapper.addTimer(dbMap);
        if (result <= 0) {
            throw new BusinessException(ResultCode.ERROR_001);
        }

        Timer timer = new Timer(); // TODO: 실제 처리된 Entity로 변경할 것
        TimerCreateResponse response = new TimerCreateResponse();
        timer.setTimerSeq(timer.getTimerSeq());
        map(timer, response);

        return response;
    }
    public List<TimerQueryResponse> getUserTimer(TimerQueryRequest query) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("userSeq", query.getUserSeq());

        List<HashMap<String, Object>> timerList = timerMapper.getUserTimer(dbMap);

        return timerList.stream()
                .map(row -> {
                    Timer timer = new Timer(); // TODO: 실제 리턴 값 대신 사용
                    TimerQueryResponse response = new TimerQueryResponse();
                    map(timer, response);
                    response.setTimerSeq(timer.getTimerSeq());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public TimerModifyResponse modifyTimer(TimerModifyRequest request) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("userSeq", request.getUserSeq());
        dbMap.put("timerSeq", request.getTimerSeq());
        dbMap.put("days", request.getDays());
        dbMap.put("hours", request.getHours());
        dbMap.put("minutes", request.getMinutes());
        dbMap.put("seconds", request.getSeconds());
        int result = timerMapper.modifyTimer(dbMap);

        if (result <= 0) {
            throw new BusinessException(ResultCode.ERROR_001);
        }
        Timer timer = new Timer(); // TODO: 실제 처리된 Entity로 변경할 것
        TimerModifyResponse response = new TimerModifyResponse();
        timer.setTimerSeq(timer.getTimerSeq());
        map(timer, response);

        return response;
    }

    public TimerDeleteResponse deleteTimer(TimerDeleteRequest timer) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", timer.getTimerSeq());

        if(timer.getTimerSeq() == null) {
            throw new BusinessException(ResultCode.ERROR_006);
        }

        int result = timerMapper.deleteTimer(dbMap);
        TimerDeleteResponse response = new TimerDeleteResponse();
        response.setStatus(result > 0);
        return  response;
    }



    private <T extends TimerDto> T map(Timer timer, T dto) {
        dto.setDays(timer.getDays());
        dto.setHours(timer.getHours());
        dto.setMinutes(timer.getMinutes());
        dto.setCreateDate(timer.getCreateDate());
        dto.setUpdateDate(timer.getUpdateDate());
        return dto;
    }
}
