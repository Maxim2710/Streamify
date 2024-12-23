package com.feedback.controller;

import com.feedback.model.Comment;
import com.feedback.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long id) {
        try {
            List<Comment> comments = commentService.getCommentsByPostId(id);
            return ResponseEntity.ok(comments);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@RequestHeader(name = "Authorization") String token,
                                              @PathVariable Long id) {
        try {
            commentService.deleteComment(token, id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
