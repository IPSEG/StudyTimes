package com.ipseg.studyTime.api.comment.service;

import com.ipseg.studyTime.api.comment.mapper.CommentMapper;
import com.ipseg.studyTime.api.comment.model.Comment;
import org.springframework.http.HttpStatus;
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

        int timerCnt = commentMapper.findTimerByTimerSeq(dbMap);

        if(timerCnt < 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        commentMapper.commentAdd(dbMap);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> getCommentList(Comment comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", comment.getTimerSeq());

        List<HashMap<String, Object>> commentList = commentMapper.getCommentList(dbMap);

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }
}
