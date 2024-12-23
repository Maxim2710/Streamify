package com.feedback.service;

import com.feedback.bom.UserBom;
import com.feedback.connector.AuthConnector;
import com.feedback.model.Comment;
import com.feedback.repository.CommentRepository;
import com.feedback.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthConnector authConnector;

    @Autowired
    private PostRepository postRepository;

    public Comment addComment(String token, Long postId, String content) {
        UserBom userBom = authConnector.getCurrentUser(token);
        Long userId = userBom.getId();

        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post not found");
        }

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post not found");
        }

        return commentRepository.findByPostId(postId);
    }

    public void deleteComment(String token, Long commentId) {
        UserBom userBom = authConnector.getCurrentUser(token);
        Long userId = userBom.getId();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        boolean isOwner = comment.getUserId().equals(userId);
        boolean isPostOwner = postRepository.findById(comment.getPostId())
                .map(post -> post.getUserId().equals(userId))
                .orElse(false);

        if (!isOwner && !isPostOwner) {
            throw new IllegalStateException("You are not authorized to delete this comment");
        }

        commentRepository.delete(comment);
    }
}
