package com.feedback.controller;

import com.feedback.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/{id}/like")
    public ResponseEntity<String> likePost(@RequestHeader(name = "Authorization") String token,
                                           @PathVariable Long id) {
        try {
            likeService.addLike(token, id);
            return ResponseEntity.ok("Post liked successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<String> unlikePost(@RequestHeader(name = "Authorization") String token,
                                             @PathVariable Long id) {
        try {
            likeService.removeLike(token, id);
            return ResponseEntity.ok("Post unliked successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

