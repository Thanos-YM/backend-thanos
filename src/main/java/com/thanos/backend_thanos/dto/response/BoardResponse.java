package com.thanos.backend_thanos.dto.response;

import com.thanos.backend_thanos.entity.Board;

import java.time.LocalDateTime;
import java.util.UUID;

public record BoardResponse(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {
    public static BoardResponse from(Board board){
        return new BoardResponse(
                board.getId(),
                board.getName(),
                board.getDescription(),
                board.getCreatedAt()
        );
    }
}
