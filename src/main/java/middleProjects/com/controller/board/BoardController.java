package middleProjects.com.controller.board;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.board.CreateBoardRequestDto;
import middleProjects.com.dto.board.CreateBoardResponseDto;
import middleProjects.com.dto.board.RetrieveBoardResponseDto;
import middleProjects.com.dto.board.UpdateBoardRequestDto;
import middleProjects.com.dto.board.UpdateBoardResponseDto;
import middleProjects.com.service.Board.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/") //게시글 작성
    public CreateBoardResponseDto createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto) {
        return boardService.createBoard(createBoardRequestDto);
    }

    @GetMapping("/") //전체 게시글 조회
    public List<RetrieveBoardResponseDto> retrieveBoardList() {
        return boardService.retrieveBoardList();
    }

    @GetMapping("/{postId}") //아이디별 게시글 조회
    public RetrieveBoardResponseDto retrieveBoard(@PathVariable Long id) {
        return boardService.retrieveBoard(id);
    }

    @PutMapping("/{postId}") //게시글 수정
    public UpdateBoardResponseDto updateBoard(@PathVariable Long id, @RequestBody UpdateBoardRequestDto updateBoardRequestDto) {
        return boardService.updateBoard(id, updateBoardRequestDto);
    }

    @DeleteMapping("/{postId}") //게시글 삭제
    public  void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}
