package com.ipseg.studyTime.repository.comment.mybatis;

import com.ipseg.studyTime.api.comment.mapper.CommentMapper;
import com.ipseg.studyTime.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class MyBatisCommentRepositoryImpl implements CommentRepository {

    private final CommentMapper commentMapper;

    @Override
    public int getTimerByTimerSeq(HashMap<String, Object> param) {
        return commentMapper.getTimerByTimerSeq(param);
    }

    @Override
    public int addComment(HashMap<String, Object> param) {
        return commentMapper.addComment(param);
    }

    @Override
    public List<HashMap<String, Object>> getCommentList(HashMap<String, Object> param) {
        return commentMapper.getCommentList(param);
    }

    @Override
    public int modifyComment(HashMap<String, Object> param) {
        return commentMapper.modifyComment(param);
    }

    @Override
    public int deleteComment(HashMap<String, Object> param) {
        return commentMapper.deleteComment(param);
    }
}
