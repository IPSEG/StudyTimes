package com.ipseg.studyTime.api.timer.dto.timerCreate;

import com.ipseg.studyTime.api.timer.dto.TimerDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimerCreateResponse extends TimerDto {
    private Integer timerSeq;
}
