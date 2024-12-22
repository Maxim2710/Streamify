package com.post.repository;

import com.post.model.Media;
import com.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByPostId(Long postId);

    Media findByUrl(String url);

    List<Media> findByMediaType(String mediaType);

    List<Media> findByPost(Post post);

}
