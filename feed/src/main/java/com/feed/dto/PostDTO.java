package com.feed.dto;

import com.feed.dto.MediaDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDTO {
    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private List<MediaDTO> mediaFiles;
}
