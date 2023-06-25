package com.ipseg.studyTime.api.comment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer commentSeq;
    private Integer timerSeq;
    private Integer userSeq;
    private String contents;
    private String createDate;
    private String updateDate;
}
