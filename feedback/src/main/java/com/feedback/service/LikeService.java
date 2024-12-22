package com.feedback.service;

import com.feedback.bom.UserBom;
import com.feedback.connector.AuthConnector;
import com.feedback.model.Like;
import com.feedback.repository.LikeRepository;
import com.feedback.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private AuthConnector authConnector;

    @Autowired
    private PostRepository postRepository;

    public void addLike(String token, Long postId) {
        UserBom userBom = authConnector.getCurrentUser(token);
        Long userId = userBom.getId();

        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post not found");
        }

        boolean alreadyLiked = likeRepository.existsByPostIdAndUserId(postId, userId);
        if (alreadyLiked) {
            throw new IllegalStateException("User has already liked this post");
        }

        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(userId);
        like.setCreatedAt(LocalDateTime.now());

        likeRepository.save(like);
    }

    public void removeLike(String token, Long postId) {
        UserBom userBom = authConnector.getCurrentUser(token);
        Long userId = userBom.getId();

        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post not found");
        }

        Like like = likeRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Like not found"));

        likeRepository.delete(like);
    }
}
