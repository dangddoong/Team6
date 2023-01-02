package middleProjects.com.service.Board;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.board.CreateBoardRequestDto;
import middleProjects.com.dto.board.CreateBoardResponseDto;
import middleProjects.com.dto.board.RetrieveBoardResponseDto;
import middleProjects.com.entity.Board;
import middleProjects.com.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public CreateBoardResponseDto createBoard(CreateBoardRequestDto createBoardRequestDto) {
        Board board = new Board(createBoardRequestDto);
        boardRepository.save(board);
        return new CreateBoardResponseDto(board);
    }

    @Transactional
    public RetrieveBoardResponseDto retrieveBoardList() {
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        List<RetrieveBoardResponseDto> retrieveBoardResponseDtoList = new ArrayList<>();
        for(Board board: boardList) {
            retrieveBoardResponseDtoList.add(new RetrieveBoardResponseDto());
        }
        return new  retrieveBoardResponseDtoList;
    }
}
