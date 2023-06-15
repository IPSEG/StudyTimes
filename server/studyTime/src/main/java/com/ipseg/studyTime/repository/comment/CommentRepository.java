package com.ipseg.studyTime.repository.comment;

import java.util.HashMap;
import java.util.List;

public interface CommentRepository {
    int getTimerByTimerSeq(HashMap<String, Object> param);
    int addComment(HashMap<String, Object> param);
    List<HashMap<String, Object>> getCommentList(HashMap<String, Object> param);
    int modifyComment(HashMap<String, Object> param);
    int deleteComment(HashMap<String, Object> param);
}
