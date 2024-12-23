package com.feedback.controller;

import com.feedback.model.Comment;
import com.feedback.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@RequestHeader(name = "Authorization") String token,
                                              @PathVariable Long id,
                                              @RequestParam String content) {
        Comment comment = commentService.addComment(token, id, content);
        return ResponseEntity.ok(comment);
    }
}
