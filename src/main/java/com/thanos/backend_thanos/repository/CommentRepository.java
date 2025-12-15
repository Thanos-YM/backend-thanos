package com.thanos.backend_thanos.repository;

import com.thanos.backend_thanos.entity.Comment;
import com.thanos.backend_thanos.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByUserId(UUID userId);
    List<Comment> findByPost(Post post);  // 특정 게시글의 댓글 목록
}
