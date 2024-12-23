package com.feed.service;

import com.feed.connector.SubscriptionsConnector;
import com.feed.dto.MediaDTO;
import com.feed.dto.PostDTO;
import com.feed.model.Media;
import com.feed.model.Post;
import com.feed.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedService {

    @Autowired
    private SubscriptionsConnector subscriptionsConnector;

    @Autowired
    private PostRepository postRepository;

    public List<PostDTO> getFeed(String token) {
        List<Long> subscriptions = subscriptionsConnector.getSubscriptions(token);

        List<Post> posts = postRepository.findByUserIdInOrderByCreatedAtDesc(subscriptions);

        return posts.stream()
                .map(this::mapToPostDTO)
                .collect(Collectors.toList());
    }

    private PostDTO mapToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setUserId(post.getUserId());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setMediaFiles(
                post.getMediaFiles().stream()
                        .map(this::mapToMediaDTO)
                        .collect(Collectors.toList())
        );
        return postDTO;
    }

    private MediaDTO mapToMediaDTO(Media media) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(media.getId());
        mediaDTO.setMediaType(media.getMediaType());
        mediaDTO.setUrl(media.getUrl());
        return mediaDTO;
    }
}
