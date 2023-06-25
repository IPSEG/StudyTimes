package com.ipseg.studyTime.api.comment.service;

import com.ipseg.studyTime.api.comment.mapper.CommentMapper;
import com.ipseg.studyTime.api.comment.model.Comment;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import com.ipseg.studyTime.common.response.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public Comment addComment(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", comment.getTimerSeq());
        dbMap.put("userSeq", comment.getUserSeq());
        dbMap.put("contents", comment.getContents());

        int timer = commentMapper.getTimerByTimerSeq(dbMap);

        if(timer < 1) {
            throw new BusinessException(ResultCode.ERROR_005);
        }

        int result = commentMapper.addComment(dbMap);
        if (result <= 0) {
            throw new BusinessException(ResultCode.ERROR_009);
        }

        return comment;
    }

    public List<Comment> getCommentList(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", comment.getTimerSeq());

        int timer = commentMapper.getTimerByTimerSeq(dbMap);

        if(timer < 1) {
            throw new BusinessException(ResultCode.ERROR_005);
        }

        List<HashMap<String, Object>> commentList = commentMapper.getCommentList(dbMap);

        return commentList.stream().map(row -> new Comment(
                (Integer) row.get("USER_SEQ"),
                (Integer) row.get("TIME_SEQ"),
                (Integer) row.get("USER_SEQ"),
                (String) row.get("CONTENTS"),
                (String) row.get("CREATE_DATE"),
                (String) row.get("UPDATE_DATE")))
                .collect(Collectors.toList());
    }

    public Comment modifyComment(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", comment.getTimerSeq());
        dbMap.put("commentSeq", comment.getCommentSeq());
        dbMap.put("contents", comment.getContents());

        if(comment.getCommentSeq() == null || comment.getTimerSeq() == null) {
            throw new BusinessException(ResultCode.ERROR_006);
        }

        int result = commentMapper.modifyComment(dbMap);
        if (result <= 0) {
            throw new BusinessException(ResultCode.ERROR_001);
        }

        return comment;
    }

    public boolean deleteComment(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("commentSeq", comment.getCommentSeq());

        if(comment.getCommentSeq() == null || comment.getTimerSeq() == null) {
            throw new BusinessException(ResultCode.ERROR_006);
        }

        int result = commentMapper.deleteComment(dbMap);
        if (result <= 0) {
            throw new BusinessException(ResultCode.ERROR_001);
        }

        return true;
    }
}
