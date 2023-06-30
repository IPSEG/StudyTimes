package com.ipseg.studyTime.api.comment.dto.commentModify;

import com.ipseg.studyTime.api.comment.dto.CommentDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentModifyResponse extends CommentDto {
    private Integer commentSeq;
}
