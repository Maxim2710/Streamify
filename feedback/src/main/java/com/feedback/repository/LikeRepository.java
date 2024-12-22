package com.feedback.repository;

import com.feedback.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostIdAndUserId(Long postId, Long userId);

    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

}
