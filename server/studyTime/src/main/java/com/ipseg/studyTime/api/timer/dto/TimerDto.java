package com.ipseg.studyTime.api.timer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimerDto {
    private Integer userSeq;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private String createDate;
    private String updateDate;
}
