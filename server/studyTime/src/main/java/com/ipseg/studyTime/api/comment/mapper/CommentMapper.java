package com.ipseg.studyTime.api.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommentMapper {
    int getTimerByTimerSeq(HashMap<String, Object> param);
    int commentAdd(HashMap<String, Object> param);
    List<HashMap<String, Object>> getCommentList(HashMap<String, Object> param);
    int modifyComment(HashMap<String, Object> param);
}
