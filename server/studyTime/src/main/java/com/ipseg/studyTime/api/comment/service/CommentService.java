package com.ipseg.studyTime.api.comment.service;

import com.ipseg.studyTime.api.comment.mapper.CommentMapper;
import com.ipseg.studyTime.api.comment.model.Comment;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public ResponseEntity<Object> addComment(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", comment.getTimerSeq());
        dbMap.put("userSeq", comment.getUserSeq());
        dbMap.put("contents", comment.getContents());

        int timer = commentMapper.getTimerByTimerSeq(dbMap);

        if(timer < 1)
            return ApiResultEntity.fail(ResultCode.ERROR_005);

        int result = commentMapper.addComment(dbMap);

        return ApiResultEntity.success(result);
    }

    public ResponseEntity<Object> getCommentList(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", comment.getTimerSeq());

        int timer = commentMapper.getTimerByTimerSeq(dbMap);

        if(timer < 1)
            return ApiResultEntity.fail(ResultCode.ERROR_005);

        List<HashMap<String, Object>> commentList = commentMapper.getCommentList(dbMap);

        return ApiResultEntity.success(commentList);
    }

    public ResponseEntity<Object> modifyComment(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", comment.getTimerSeq());
        dbMap.put("commentSeq", comment.getCommentSeq());
        dbMap.put("contents", comment.getContents());

        if(comment.getCommentSeq() == null || comment.getTimerSeq() == null) {
            return ApiResultEntity.fail(ResultCode.ERROR_006);
        }

        commentMapper.modifyComment(dbMap);
        return ApiResultEntity.success();
    }

    public ResponseEntity<Object> deleteComment(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("commentSeq", comment.getCommentSeq());

        if(comment.getCommentSeq() == null || comment.getTimerSeq() == null) {
            return ApiResultEntity.fail(ResultCode.ERROR_006);
        }

        int result = commentMapper.deleteComment(dbMap);

        return ApiResultEntity.success(result);
    }
}
