package com.ipseg.studyTime.api.comment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private Integer commentSeq;
    private Integer timerSeq;
    private Integer userSeq;
    private String contents;
    private String createDate;
    private String updateDate;
}
