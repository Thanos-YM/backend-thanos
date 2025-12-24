package com.thanos.backend_thanos.repository;

import com.thanos.backend_thanos.entity.Board;
import com.thanos.backend_thanos.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByUserId(UUID userId);
    List<Post> findByBoard(Board board);  // 특정 게시판의 게시글 목록
}
