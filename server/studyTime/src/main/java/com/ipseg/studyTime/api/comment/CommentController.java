package com.ipseg.studyTime.api.comment;

import com.ipseg.studyTime.api.comment.model.Comment;
import com.ipseg.studyTime.api.timer.model.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CommentController {

    @RequestMapping("/addTimer")
    public ResponseEntity<Object> addComment(@RequestBody Comment comment) {
        log.info("CommentController - addComment : {}", comment);

    }
}
