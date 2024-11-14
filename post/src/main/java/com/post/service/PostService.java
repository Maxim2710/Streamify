package com.post.service;

import com.post.bom.UserBom;
import com.post.connector.AuthConnector;
import com.post.dto.MediaDTO;
import com.post.dto.PostWithMediaDTO;
import com.post.model.Media;
import com.post.model.Post;
import com.post.repository.MediaRepository;
import com.post.repository.PostRepository;
import com.post.util.RemoteFileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RemoteFileUploader remoteFileUploader;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private AuthConnector authConnector;

    public PostWithMediaDTO createPost(String token, String content, List<MultipartFile> mediaFiles) throws IOException {
        UserBom userBom = authConnector.getCurrentUser(token);

        Post post = new Post();
        post.setContent(content != null ? content : "");
        post.setUserId(userBom.getId());
        post = postRepository.save(post);

        List<Media> mediaList = new ArrayList<>();
        if (mediaFiles != null && !mediaFiles.isEmpty()) {
            for (MultipartFile mediaFile : mediaFiles) {
                String mediaType = mediaFile.getContentType().startsWith("image") ? "IMAGE" : "VIDEO";
                String fileName = mediaFile.getOriginalFilename();
                String fileUrl = remoteFileUploader.uploadFileOnServer(mediaFile, fileName, "posts");

                Media media = new Media();
                media.setPost(post);
                media.setMediaType(mediaType);
                media.setUrl(fileUrl);
                mediaList.add(media);
            }
            mediaRepository.saveAll(mediaList);
        }

        List<MediaDTO> mediaDTOs = mediaList.stream()
                .map(media -> new MediaDTO(media.getMediaType(), media.getUrl()))
                .collect(Collectors.toList());

        return new PostWithMediaDTO(post.getId(), post.getUserId(), post.getContent(), post.getCreatedAt(), mediaDTOs);
    }

}
