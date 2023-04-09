package com.ipseg.studyTime.api.timer;

import com.ipseg.studyTime.api.timer.model.Timer;
import com.ipseg.studyTime.api.timer.service.TimerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping(value = "/timer")
public class TimerController {

    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @RequestMapping("/addTimer")
    public ResponseEntity<Object> addTimer(@RequestBody Timer timer) {
        log.info("TimerController - addTimer : {}", timer);
        return timerService.addTimer(timer);
    }

    @RequestMapping("/getUserTimer")
    public ResponseEntity<Object> getUserTimer(@RequestBody Timer timer) {
        log.info("TimerController - getUserTimer : {}", timer);
        return timerService.getUserTimer(timer);
    }

    @RequestMapping("/modifyTimer")
    public ResponseEntity<Object> modifyTimer(@RequestBody Timer timer) {
        log.info("TimerController - modifyTimer : {}", timer);
        return timerService.modifyTimer(timer);
    }
}
