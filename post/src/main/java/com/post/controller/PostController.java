package com.post.controller;

import com.post.dto.PostWithMediaDTO;
import com.post.model.Post;
import com.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostWithMediaDTO> createPost(@RequestHeader(name = "Authorization") String token,
                                                       @RequestParam(required = false) String content,
                                                       @RequestParam(required = false) List<MultipartFile> mediaFiles) throws IOException {
        PostWithMediaDTO postWithMediaDTO = postService.createPost(token, content, mediaFiles);
        return ResponseEntity.ok(postWithMediaDTO);
    }

    @PostMapping("/{id}/repost")
    public ResponseEntity<PostWithMediaDTO> repostPost(@RequestHeader(name = "Authorization") String token,
                                                       @PathVariable Long id) {
        PostWithMediaDTO repostedPost = postService.repostPost(token, id);
        return ResponseEntity.ok(repostedPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostWithMediaDTO> getPostById(@PathVariable Long id) {
        PostWithMediaDTO postWithMediaDTO = postService.getPostById(id);
        return ResponseEntity.ok(postWithMediaDTO);
    }

}
