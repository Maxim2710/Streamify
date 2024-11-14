package com.auth.controller;


import com.auth.bom.UserBom;
import com.auth.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/getCurrentUser")
    public UserBom getCurrentUser(@RequestHeader(name = "Authorization") String token) {
        return postService.getCurrentUser(token);
    }

}
