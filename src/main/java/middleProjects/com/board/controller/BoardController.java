package middleProjects.com.board.controller;

import lombok.RequiredArgsConstructor;
import middleProjects.com.board.dto.*;
import middleProjects.com.board.service.BoardServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("/") //게시글 작성
    public CreateBoardResponseDto createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto) {
        return boardServiceImpl.createBoard(createBoardRequestDto);
    }

    @GetMapping("/") //전체 게시글 조회
    public List<RetrieveBoardResponseDto> retrieveBoardList() {
        return boardServiceImpl.retrieveBoardList();
    }

    @GetMapping("/{boardId}") //아이디별 게시글 조회
    public RetrieveBoardResponseDto retrieveBoard(@PathVariable Long boardId) {
        return boardServiceImpl.retrieveBoard(boardId);
    }

    @PutMapping("/{boardId}") //게시글 수정
    public UpdateBoardResponseDto updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequestDto updateBoardRequestDto) {
        return boardServiceImpl.updateBoard(boardId, updateBoardRequestDto);
    }

    @DeleteMapping("/{boardId}") //게시글 삭제
    public void deleteBoard(@PathVariable Long boardId) {
        boardServiceImpl.deleteBoard(boardId);
    }

    @PostMapping("/recommendation/{boardId}")
    public void recommendBoard(@PathVariable Long boardId) {
        boardServiceImpl.recommendBoard(boardId);
    }
}
