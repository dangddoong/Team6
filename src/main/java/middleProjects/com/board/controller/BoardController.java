package middleProjects.com.board.controller;

import lombok.RequiredArgsConstructor;
import middleProjects.com.board.dto.*;
import middleProjects.com.board.service.BoardService;
import middleProjects.com.security.members.MemberDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/") //게시글 작성
    public CreateBoardResponseDto createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        return boardService.saveBoard(createBoardRequestDto,memberDetails.getMember());
    }

    @GetMapping("/") //전체 게시글 조회
    public List<RetrieveBoardResponseDto> retrieveBoardList() {
        return boardService.retrieveBoardList();
    }

    @GetMapping("/{postId}") // 아이디별 게시글 조회
    public RetrieveBoardResponseDto retrieveBoard(@PathVariable Long postId) {
        return boardService.retrieveBoard(postId);
    }

    @PutMapping("/{postId}") //게시글 수정
    public UpdateBoardResponseDto updateBoard(@PathVariable Long postId, @RequestBody UpdateBoardRequestDto updateBoardRequestDto) {
        return boardService.updateBoard(postId, updateBoardRequestDto);
    }

    @DeleteMapping("/{postId}") //게시글 삭제
    public void deleteBoard(@PathVariable Long postId) {
        boardService.deleteBoard(postId);
    }




}
