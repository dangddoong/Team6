package middleProjects.com.controller.Board;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.board.CreateBoardRequestDto;
import middleProjects.com.dto.board.CreateBoardResponseDto;
import middleProjects.com.service.Board.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards") //게시글 작성
    public CreateBoardResponseDto createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto) {
        return boardService.createBoard(createBoardRequestDto);
    }

    @GetMapping("/boards")
    public List<CreateBoardResponseDto>



}
