package com.ipseg.studyTime.api.comment;

import com.ipseg.studyTime.api.comment.dto.CommentDelete.CommentDeleteRequest;
import com.ipseg.studyTime.api.comment.dto.CommentDelete.CommentDeleteResponse;
import com.ipseg.studyTime.api.comment.dto.commentCreate.CommentCreateRequest;
import com.ipseg.studyTime.api.comment.dto.commentCreate.CommentCreateResponse;
import com.ipseg.studyTime.api.comment.dto.commentModify.CommentModifyRequest;
import com.ipseg.studyTime.api.comment.dto.commentModify.CommentModifyResponse;
import com.ipseg.studyTime.api.comment.dto.commentQuery.CommentQueryRequest;
import com.ipseg.studyTime.api.comment.dto.commentQuery.CommentQueryResponse;
import com.ipseg.studyTime.api.comment.model.Comment;
import com.ipseg.studyTime.api.comment.service.CommentService;
import com.ipseg.studyTime.common.response.ApiResult;
import com.ipseg.studyTime.common.response.ApiResultEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ApiOperation(value = "타이머 메모 추가")
    public ResponseEntity<ApiResult<CommentCreateResponse>> addComment(@RequestBody CommentCreateRequest comment) {
        return ApiResultEntity.success(commentService.addComment(comment));
    }

    @GetMapping
    @ApiOperation(value = "타이머 메모 조회")
    public ResponseEntity<ApiResult<List<CommentQueryResponse>>> getComment(@RequestBody CommentQueryRequest comment) {
        List<CommentQueryResponse> comments = commentService.getCommentList(comment);
        return ApiResultEntity.success(comments);
    }

    @PutMapping
    @ApiOperation(value = "타이머 메모 수정")
    public ResponseEntity<ApiResult<List<CommentModifyResponse>>> modifyComment(@RequestBody CommentModifyRequest comment) {
        List<CommentModifyResponse> list = commentService.modifyComment(comment);
        return ApiResultEntity.success(list);
    }

    @DeleteMapping
    @ApiOperation(value = "타이머 메모 삭제")
    public ResponseEntity<ApiResult<CommentDeleteResponse>> deleteComment(@RequestBody CommentDeleteRequest comment) {
        return ApiResultEntity.success(commentService.deleteComment(comment));
    }
}
