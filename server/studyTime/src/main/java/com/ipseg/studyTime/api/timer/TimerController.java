package com.ipseg.studyTime.api.timer;

import com.ipseg.studyTime.api.timer.dto.TimerQuery.TimerQueryRequest;
import com.ipseg.studyTime.api.timer.dto.TimerQuery.TimerQueryResponse;
import com.ipseg.studyTime.api.timer.dto.timerCreate.TimerCreateRequest;
import com.ipseg.studyTime.api.timer.dto.timerCreate.TimerCreateResponse;
import com.ipseg.studyTime.api.timer.dto.timerDelete.TimerDeleteRequest;
import com.ipseg.studyTime.api.timer.dto.timerDelete.TimerDeleteResponse;
import com.ipseg.studyTime.api.timer.dto.timerModify.TimerModifyRequest;
import com.ipseg.studyTime.api.timer.dto.timerModify.TimerModifyResponse;
import com.ipseg.studyTime.api.timer.model.Timer;
import com.ipseg.studyTime.api.timer.service.TimerService;
import com.ipseg.studyTime.common.response.ApiResult;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResult<TimerCreateResponse>> addTimer(@RequestBody TimerCreateRequest body) {
        TimerCreateResponse response = timerService.addTimer(body);
        ResponseEntity<ApiResult<TimerCreateResponse>> success = ApiResultEntity.success(response);
        return success;
    }

    @GetMapping
    @ApiOperation(value = "타이머 조회")
    public ResponseEntity<ApiResult<List<TimerQueryResponse>>> getUserTimer(@RequestBody TimerQueryRequest query) {
        ResponseEntity<ApiResult<List<TimerQueryResponse>>> success = ApiResultEntity.success(timerService.getUserTimer(query));
        return success;
    }

    @PutMapping
    @ApiOperation(value = "타이머 수정")
    public ResponseEntity<ApiResult<TimerModifyResponse>> modifyTimer(@RequestBody TimerModifyRequest timer) {
        ResponseEntity<ApiResult<TimerModifyResponse>> success = ApiResultEntity.success(timerService.modifyTimer(timer));
        return success;
    }

    @DeleteMapping
    @ApiOperation(value = "타이머 삭제")
    public ResponseEntity<ApiResult<TimerDeleteResponse>> deleteTimer(@RequestBody TimerDeleteRequest timer) {
        ResponseEntity<ApiResult<TimerDeleteResponse>> success = ApiResultEntity.success(timerService.deleteTimer(timer));
        return success;
    }
}
