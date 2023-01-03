package middleProjects.com.controller.board;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.board.CreateBoardRequestDto;
import middleProjects.com.dto.board.RetrieveBoardResponseDto;
import middleProjects.com.dto.board.UpdateBoardRequestDto;
import middleProjects.com.dto.board.UpdateBoardResponseDto;
import middleProjects.com.security.members.MemberDetails;
import middleProjects.com.service.Board.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/") //게시글 작성
    public HttpStatus createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
       boardService.createBoard(createBoardRequestDto,memberDetails.getMember());
       return HttpStatus.CREATED;
    }

    @GetMapping("/") //전체 게시글 조회
    public List<RetrieveBoardResponseDto> retrieveBoardList() {
        return boardService.retrieveBoardList();
    }

    @GetMapping("/{postId}") //아이디별 게시글 조회
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
