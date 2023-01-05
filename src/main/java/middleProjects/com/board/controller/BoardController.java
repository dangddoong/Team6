package middleProjects.com.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import middleProjects.com.board.dto.*;
import middleProjects.com.board.service.BoardService;
import middleProjects.com.board.service.BoardServiceImpl;
import middleProjects.com.security.members.MemberDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/post") //게시글 작성
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        boardService.createBoard(createBoardRequestDto, memberDetails.getMember());
        return new ResponseEntity<>("게시글 생성 완료", HttpStatus.OK);
    }

    @GetMapping("/") //전체 게시글 조회
    public List<RetrieveBoardResponseDto> retrieveBoardList() {
        return boardService.retrieveBoardList();
    }

    @GetMapping("/{boardId}") //아이디별 게시글 조회
    public RetrieveBoardResponseDto retrieveBoard(@PathVariable Long boardId) {
        return boardService.retrieveBoard(boardId);
    }

    @GetMapping("/pagination/{pageChoice}") //게시물 페이징 조회(10개단위로 나뉨)
    public List<GetBoardResponseDto> getBoardListToPagination(@PathVariable int pageChoice) {
        return boardService.getBoardListToPagination(pageChoice);
    }

    @PutMapping("/{boardId}") //게시글 수정
    public ResponseEntity<UpdateBoardResponseDto> updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequestDto updateBoardRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        return new ResponseEntity<>( boardService.updateBoard(boardId, updateBoardRequestDto, memberDetails.getMember()),HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}") //게시글 삭제
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal MemberDetails memberDetails) {
        boardService.deleteBoard(boardId, memberDetails.getMember());
        return new ResponseEntity<>("게시글 삭제 완료",HttpStatus.OK);
    }

    @PostMapping("/recommendation/{boardId}") //게시글 좋아요
    public ResponseEntity<String> recommendBoard(@PathVariable Long boardId, @AuthenticationPrincipal MemberDetails memberDetails) {
        boardService.recommendBoard(boardId, memberDetails.getMember());
        return new ResponseEntity<>("게시물 좋아요 완료", HttpStatus.CREATED);
    }

    @PostMapping("/unrecommendation/{boardId}") //게시글 좋아요 취소
    public ResponseEntity<String> unRecommendBoard(@PathVariable Long boardId, @AuthenticationPrincipal MemberDetails memberDetails) {
        boardService.unRecommendBoard(boardId, memberDetails.getMember());
        return new ResponseEntity<>("게시물 좋아요 취소완료", HttpStatus.OK);
    }

}
