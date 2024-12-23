package com.feed.controller;

import com.feed.dto.PostDTO;
import com.feed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getFeed(@RequestHeader(name = "Authorization") String token) {
        List<PostDTO> feed = feedService.getFeed(token);
        return ResponseEntity.ok(feed);
    }
}
