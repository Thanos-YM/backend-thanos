package com.thanos.backend_thanos.service;

import com.thanos.backend_thanos.dto.request.BoardRequest;
import com.thanos.backend_thanos.dto.response.BoardResponse;
import com.thanos.backend_thanos.entity.Board;
import com.thanos.backend_thanos.repository.BoardRepository;
import com.thanos.backend_thanos.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 게시판 생성
    @Transactional
    public BoardResponse createBoard(UUID userId, BoardRequest request) {
        Board board = Board.builder()
                .userId(userId)
                .name(request.name())
                .description(request.description())
                .build();

        Board savedBoard = boardRepository.save(board);
        return BoardResponse.from(savedBoard);
    }

    // 게시판 전체 목록 조회
    public List<BoardResponse> getAllBoards() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponse::from)
                .toList();
    }

    // 특정 유저의 게시판 목록 조회
    public List<BoardResponse> getBoardsByUserId(UUID userId) {
        return boardRepository.findByUserId(userId)
                .stream()
                .map(BoardResponse::from)
                .toList();
    }

    // 게시판 상세 조회
    public BoardResponse getBoard(UUID boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));
        return BoardResponse.from(board);
    }

    // 게시판 수정
    @Transactional
    public BoardResponse updateBoard(UUID boardId, UUID userId, BoardRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));

        // 본인 게시판인지 확인
        if (!board.getUserId().equals(userId)) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        board.update(request.name(), request.description());
        return BoardResponse.from(board);
    }

    // 게시판 삭제
    @Transactional
    public void deleteBoard(UUID boardId, UUID userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));

        if (!board.getUserId().equals(userId)) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        boardRepository.delete(board);
    }

}
