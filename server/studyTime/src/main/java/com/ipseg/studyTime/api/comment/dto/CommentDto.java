package com.ipseg.studyTime.api.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Integer timerSeq;
    private Integer userSeq;
    private String contents;
    private String createDate;
    private String updateDate;
}
