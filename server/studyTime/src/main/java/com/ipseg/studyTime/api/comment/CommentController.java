package com.ipseg.studyTime.api.comment;

import com.ipseg.studyTime.api.comment.model.Comment;
import com.ipseg.studyTime.api.comment.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping
    @ApiOperation(value = "타이머 메모 조회")
    public ResponseEntity<Object> getComment(@RequestBody Comment comment) {
        return commentService.getCommentList(comment);
    }

    @PutMapping
    @ApiOperation(value = "타이머 메모 수정")
    public ResponseEntity<Object> modifyComment(@RequestBody Comment comment) {
        return commentService.modifyComment(comment);
    }

    @DeleteMapping
    @ApiOperation(value = "타이머 메모 삭제")
    public ResponseEntity<Object> deleteComment(@RequestBody Comment comment) {
        return commentService.deleteComment(comment);
    }
}
