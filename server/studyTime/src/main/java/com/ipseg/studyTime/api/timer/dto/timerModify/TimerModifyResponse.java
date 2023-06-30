package com.ipseg.studyTime.api.timer.dto.timerModify;

import com.ipseg.studyTime.api.timer.dto.TimerDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimerModifyResponse extends TimerDto {
    private Integer userSeq;
}
