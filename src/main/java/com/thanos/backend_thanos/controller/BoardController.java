package com.thanos.backend_thanos.controller;

import com.thanos.backend_thanos.common.BaseResponse;
import com.thanos.backend_thanos.dto.request.BoardRequest;
import com.thanos.backend_thanos.dto.response.BoardResponse;
import com.thanos.backend_thanos.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.UUID;

@Tag(name = "Board", description = "게시판 CRUD API")
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "게시판 생성")
    @PostMapping
    public ResponseEntity<BaseResponse<BoardResponse>> createBoard(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody BoardRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        BoardResponse response = boardService.createBoard(userId, request);
        return ResponseEntity.ok(BaseResponse.ok(response));
    }

    @Operation(summary = "전체 게시판 조회")
    @GetMapping
    public ResponseEntity<BaseResponse<List<BoardResponse>>> getAllBoards() {
        List<BoardResponse> response = boardService.getAllBoards();
        return ResponseEntity.ok(BaseResponse.ok(response));
    }

    @Operation(summary = "특정 게시판 상세 조회")
    @GetMapping("/{boardId}")
    public ResponseEntity<BaseResponse<BoardResponse>> getBoard(@PathVariable UUID boardId) {
        BoardResponse response = boardService.getBoard(boardId);
        return ResponseEntity.ok(BaseResponse.ok(response));
    }

    @Operation(summary = "게시판 수정")
    @PutMapping("/{boardId}")
    public ResponseEntity<BaseResponse<BoardResponse>> updateBoard(
            @PathVariable UUID boardId,
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody BoardRequest request
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        BoardResponse response = boardService.updateBoard(boardId, userId, request);
        return ResponseEntity.ok(BaseResponse.ok(response));
    }


    @Operation(summary = "게시판 삭제")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<BaseResponse<BoardResponse>> deleteBoard(
            @PathVariable UUID boardId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        boardService.deleteBoard(boardId, userId);
        return ResponseEntity.ok(BaseResponse.ok(null));
    }
}
