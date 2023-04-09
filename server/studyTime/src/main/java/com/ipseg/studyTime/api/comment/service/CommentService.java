package com.ipseg.studyTime.api.comment.service;

import com.ipseg.studyTime.api.comment.mapper.CommentMapper;
import com.ipseg.studyTime.api.comment.model.Comment;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public int addComment(Comment comment) {
        HashMap<String, Object> param = new HashMap<>();

        boolean timerSeq = commentMapper.findTimerByTimerSeq(param);
    }

}
