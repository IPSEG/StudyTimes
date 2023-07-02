package com.ipseg.studyTime.api.timer.dto.TimerQuery;

import com.ipseg.studyTime.api.timer.dto.TimerDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimerQueryResponse extends TimerDto {
    private Integer timerSeq;
}
