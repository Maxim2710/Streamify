package com.post.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Repost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long originalPostId;

    private Long userId;

    private LocalDateTime createdAt = LocalDateTime.now();
}