package com.ipseg.studyTime.api.timer.model;

import lombok.*;

@Getter
@Setter
@Builder
public class Timer {
    private Integer timerSeq;
    private Integer userSeq;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private String createDate;
    private String updateDate;
}
