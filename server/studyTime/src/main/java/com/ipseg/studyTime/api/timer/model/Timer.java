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

    @Getter
    @Setter
    static public class CreateRequest {
        private Integer userSeq;
        private int days;
        private int hours;
        private int minutes;
        private int seconds;
        private String createDate;
        private String updateDate;

        public static Timer toEntity(CreateRequest request) {
            return Timer.builder()
                    .userSeq(request.userSeq)
                    .days(request.days)
                    .hours(request.hours)
                    .minutes(request.minutes)
                    .seconds(request.seconds)
                    .createDate(request.createDate)
                    .updateDate(request.updateDate)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    static public class CreateResponse {
        private Integer timerSeq;
        private Integer userSeq;
        private int days;
        private int hours;
        private int minutes;
        private int seconds;
        private String createDate;
        private String updateDate;

        public static CreateResponse of(Timer entity) {
            return CreateResponse.builder()
                    .timerSeq(entity.getTimerSeq())
                    .userSeq(entity.getUserSeq())
                    .days(entity.getDays())
                    .hours(entity.getHours())
                    .minutes(entity.getMinutes())
                    .seconds(entity.getSeconds())
                    .createDate(entity.getCreateDate())
                    .updateDate(entity.getUpdateDate())
                    .build();
        }
    }

}
