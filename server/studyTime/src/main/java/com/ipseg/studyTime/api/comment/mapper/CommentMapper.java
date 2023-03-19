package com.ipseg.studyTime.api.comment.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface CommentMapper {
    boolean findTimerByTimerSeq(HashMap<String, Object> param);
}
