package com.ipseg.studyTime.api.comment;

import com.ipseg.studyTime.api.comment.model.Comment;
import com.ipseg.studyTime.api.comment.service.CommentService;
import com.ipseg.studyTime.api.timer.model.Timer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ApiOperation(value = "타이머 메모 추가")
    public ResponseEntity<Object> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @RequestMapping(value = "/getComment", method = RequestMethod.POST)
    @ApiOperation(value = "타이머 메모 조회")
    public ResponseEntity<Object> getComment(@RequestBody Comment comment) {
        return commentService.getCommentList(comment);
    }

    @RequestMapping(value = "/modifyComment", method = RequestMethod.POST)
    @ApiOperation(value = "타이머 수정")
    public ResponseEntity<Object> modifyComment(@RequestBody Comment comment) {
        return commentService.modifyComment(comment);
    }
}
