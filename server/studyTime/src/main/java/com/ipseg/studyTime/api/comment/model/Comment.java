package com.ipseg.studyTime.api.comment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private int commentSeq;
    private int timerSeq;
    private String content;
    private String userKey;
    private String date;
}
