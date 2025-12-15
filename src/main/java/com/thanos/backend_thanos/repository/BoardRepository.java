package com.thanos.backend_thanos.repository;

import com.thanos.backend_thanos.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
    List<Board> findByUserId(UUID userId);
}
