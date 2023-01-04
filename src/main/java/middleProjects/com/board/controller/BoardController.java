package middleProjects.com.board.controller;

import lombok.RequiredArgsConstructor;
import middleProjects.com.board.dto.*;
import middleProjects.com.board.service.BoardServiceImpl;
import middleProjects.com.security.members.MemberDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("/") //게시글 작성
    public ResponseEntity<String> createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        boardServiceImpl.createBoard(createBoardRequestDto, memberDetails.getMember());
        return new ResponseEntity<>("게시글 생성 완료", HttpStatus.OK);
    }

    @GetMapping("/") //전체 게시글 조회
    public List<RetrieveBoardResponseDto> retrieveBoardList() {
        return boardServiceImpl.retrieveBoardList();
    }

    @GetMapping("/{boardId}") //아이디별 게시글 조회
    public RetrieveBoardResponseDto retrieveBoard(@PathVariable Long boardId) {
        return boardServiceImpl.retrieveBoard(boardId);
    }

    @GetMapping("/pagination/{pageChoice}") //게시물 페이징 조회(10개단위로 나뉨)
    public List<GetBoardResponseDto> getBoardListToPagination(@PathVariable Optional<Integer> pageChoice) {
        return boardServiceImpl.getBoardListToPagination(pageChoice.orElse(1));
    }

    @PutMapping("/{boardId}") //게시글 수정
    public UpdateBoardResponseDto updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequestDto updateBoardRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        return boardServiceImpl.updateBoard(boardId, updateBoardRequestDto, memberDetails.getMember());
    }

    @DeleteMapping("/{boardId}") //게시글 삭제
    public void deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal MemberDetails memberDetails) {
        boardServiceImpl.deleteBoard(boardId, memberDetails.getMember());
    }

    @PostMapping("/recommendation/{boardId}")
    public ResponseEntity<String> recommendBoard(@PathVariable Long boardId, @AuthenticationPrincipal MemberDetails memberDetails) {
        boardServiceImpl.recommendBoard(boardId, memberDetails.getMember());
        return new ResponseEntity<>("게시물 좋아요 완료", HttpStatus.CREATED);
    }

    @PostMapping("/unrecommendation/{boardId}")
    public ResponseEntity<String> unRecommendBoard(@PathVariable Long boardId, @AuthenticationPrincipal MemberDetails memberDetails) {
        boardServiceImpl.unRecommendBoard(boardId, memberDetails.getMember());
        return new ResponseEntity<>("게시물 좋아요 취소완료", HttpStatus.OK);
    }

}
