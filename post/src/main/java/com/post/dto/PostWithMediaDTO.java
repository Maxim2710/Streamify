package com.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PostWithMediaDTO {
    private Long postId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private List<MediaDTO> mediaFiles;
}
