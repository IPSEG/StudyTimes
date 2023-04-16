package com.ipseg.studyTime.api.timer;

import com.ipseg.studyTime.api.timer.model.Timer;
import com.ipseg.studyTime.api.timer.service.TimerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    public ResponseEntity<Object> addTimer(@RequestBody Timer timer) {
        return timerService.addTimer(timer);
    }

    @GetMapping
    @ApiOperation(value = "타이머 조회")
    public ResponseEntity<Object> getUserTimer(@RequestBody Timer timer) {
        return timerService.getUserTimer(timer);
    }

    @PutMapping
    @ApiOperation(value = "타이머 수정")
    public ResponseEntity<Object> modifyTimer(@RequestBody Timer timer) {
        return timerService.modifyTimer(timer);
    }

    @DeleteMapping
    @ApiOperation(value = "타이머 삭제")
    public ResponseEntity<Object> deleteTimer(@RequestBody Timer timer) { return timerService.deleteTimer(timer);}
}
