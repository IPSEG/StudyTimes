package com.ipseg.studyTime.api.comment.service;

import com.ipseg.studyTime.api.comment.dto.CommentDelete.CommentDeleteRequest;
import com.ipseg.studyTime.api.comment.dto.CommentDelete.CommentDeleteResponse;
import com.ipseg.studyTime.api.comment.dto.CommentDto;
import com.ipseg.studyTime.api.comment.dto.commentCreate.CommentCreateRequest;
import com.ipseg.studyTime.api.comment.dto.commentCreate.CommentCreateResponse;
import com.ipseg.studyTime.api.comment.dto.commentModify.CommentModifyRequest;
import com.ipseg.studyTime.api.comment.dto.commentModify.CommentModifyResponse;
import com.ipseg.studyTime.api.comment.dto.commentQuery.CommentQueryRequest;
import com.ipseg.studyTime.api.comment.dto.commentQuery.CommentQueryResponse;
import com.ipseg.studyTime.api.comment.mapper.CommentMapper;
import com.ipseg.studyTime.api.comment.model.Comment;
import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.BusinessException;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public CommentCreateResponse addComment(CommentCreateRequest request) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", request.getTimerSeq());
        dbMap.put("userSeq", request.getUserSeq());
        dbMap.put("contents", request.getContents());

        int timer = commentMapper.getTimerByTimerSeq(dbMap);

        if(timer < 1) {
            throw new BusinessException(ResultCode.ERROR_005);
        }

        int result = commentMapper.addComment(dbMap);
        if (result <= 0) {
            throw new BusinessException(ResultCode.ERROR_009);
        }

        Comment comment = new Comment();
        CommentCreateResponse response = new CommentCreateResponse();
        response.setTimerSeq(comment.getTimerSeq());
        map(response, comment);
        return response;
    }

    public List<CommentQueryResponse> getCommentList(CommentQueryRequest comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("timerSeq", comment.getTimerSeq());

        int timer = commentMapper.getTimerByTimerSeq(dbMap);

        if(timer < 1) {
            throw new BusinessException(ResultCode.ERROR_005);
        }

        List<HashMap<String, Object>> commentList = commentMapper.getCommentList(dbMap);

        // TODO : 리스트 변환
        List<CommentQueryResponse> list =  new ArrayList<>();
        list.add(new CommentQueryResponse());
        return list;
    }

    public List<CommentModifyResponse> modifyComment(CommentModifyRequest comment) {
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

        // TODO : 리스트 변환
        List<CommentModifyResponse> list =  new ArrayList<>();
        list.add(new CommentModifyResponse());
        return list;
    }

    public CommentDeleteResponse deleteComment(CommentDeleteRequest comment) {
        HashMap<String, Object> dbMap = new HashMap<>();
        dbMap.put("commentSeq", comment.getCommentSeq());

        if(comment.getCommentSeq() == null || comment.getTimerSeq() == null) {
            throw new BusinessException(ResultCode.ERROR_006);
        }

        int result = commentMapper.deleteComment(dbMap);
        CommentDeleteResponse response = new CommentDeleteResponse();
        response.setStatus(result > 0);
        return response;
    }

    private <T extends CommentDto> T map(T target, Comment comment) {
        target.setTimerSeq(comment.getTimerSeq());
        target.setUserSeq(comment.getUserSeq());
        target.setContents(comment.getContents());
        target.setCreateDate(comment.getCreateDate());
        target.setUpdateDate(comment.getUpdateDate());
        return target;
    }
}
