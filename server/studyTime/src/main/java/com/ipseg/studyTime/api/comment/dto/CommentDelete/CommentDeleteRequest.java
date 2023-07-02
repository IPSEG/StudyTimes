package com.ipseg.studyTime.api.comment.dto.CommentDelete;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDeleteRequest {
    private Integer commentSeq;
    private Integer timerSeq;
}
