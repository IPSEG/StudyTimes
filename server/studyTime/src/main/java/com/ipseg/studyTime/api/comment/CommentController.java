package com.ipseg.studyTime.api.comment;

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
    public ResponseEntity<ApiResult<Comment>> addComment(@RequestBody Comment comment) {
        comment = commentService.addComment(comment);
        return ApiResultEntity.success(comment);
    }

    @GetMapping
    @ApiOperation(value = "타이머 메모 조회")
    public ResponseEntity<ApiResult<List<Comment>>> getComment(@RequestBody Comment comment) {
        List<Comment> comments = commentService.getCommentList(comment);
        return ApiResultEntity.success(comments);
    }

    @PutMapping
    @ApiOperation(value = "타이머 메모 수정")
    public ResponseEntity<ApiResult<Comment>> modifyComment(@RequestBody Comment comment) {
        comment = commentService.modifyComment(comment);
        return ApiResultEntity.success(comment);
    }

    @DeleteMapping
    @ApiOperation(value = "타이머 메모 삭제")
    public ResponseEntity<ApiResult<Boolean>> deleteComment(@RequestBody Comment comment) {
        return ApiResultEntity.success(commentService.deleteComment(comment));
    }
}
