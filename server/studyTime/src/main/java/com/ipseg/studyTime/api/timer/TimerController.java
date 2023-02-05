package com.ipseg.studyTime.api.timer;

import com.ipseg.studyTime.api.timer.service.TimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController("/timer")
public class TimerController {

    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @RequestMapping("/add")
    public ResponseEntity<Object> addTimer(@RequestBody HashMap<String, Object> param) {
        return timerService.addTimer(param);
    }
}
