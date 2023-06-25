package com.ipseg.studyTime.api.timer;

import com.ipseg.studyTime.api.timer.model.Timer;
import com.ipseg.studyTime.api.timer.model.TimerCreateRequest;
import com.ipseg.studyTime.api.timer.model.TimerCreateResponse;
import com.ipseg.studyTime.api.timer.service.TimerService;
import com.ipseg.studyTime.common.response.ApiResult;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/timer")
public class TimerController {

    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @PostMapping
    @ApiOperation(value = "타이머 추가")
    public ResponseEntity<ApiResult<Timer.CreateResponse>> addTimer(@RequestBody Timer.CreateRequest body) {
        Timer timer = Timer.CreateRequest.toEntity(body);
        timer = timerService.addTimer(timer);
        Timer.CreateResponse response = Timer.CreateResponse.of(timer);
        ResponseEntity<ApiResult<Timer.CreateResponse>> success = ApiResultEntity.success(response);
        return success;
    }

    @GetMapping
    @ApiOperation(value = "타이머 조회")
    public ResponseEntity<ApiResult<List<Timer>>> getUserTimer(@RequestBody Timer timer) {
        ResponseEntity<ApiResult<List<Timer>>> success = ApiResultEntity.success(timerService.getUserTimer(timer));
        return success;
    }

    @PutMapping
    @ApiOperation(value = "타이머 수정")
    public ResponseEntity<ApiResult<Timer>> modifyTimer(@RequestBody Timer timer) {
        ResponseEntity<ApiResult<Timer>> success = ApiResultEntity.success(timerService.modifyTimer(timer));
        return success;
    }

    @DeleteMapping
    @ApiOperation(value = "타이머 삭제")
    public ResponseEntity<ApiResult<Boolean>> deleteTimer(@RequestBody Timer timer) {
        ResponseEntity<ApiResult<Boolean>> success = ApiResultEntity.success(timerService.deleteTimer(timer));
        return success;
    }
}
