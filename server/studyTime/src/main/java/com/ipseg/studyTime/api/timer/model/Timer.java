package com.ipseg.studyTime.api.timer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timer {
    private int timerSeq;
    private int day;
    private int hour;
    private int minute;
    private int seconds;
    private String userSeq;
    private String createDate;
    private String updateDate
}
