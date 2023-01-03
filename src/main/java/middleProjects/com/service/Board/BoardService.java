package middleProjects.com.service.Board;

import middleProjects.com.dto.board.*;

import java.util.List;

public interface BoardService {

    CreateBoardResponseDto createBoard(CreateBoardRequestDto createBoardRequestDto);

    List<RetrieveBoardResponseDto> retrieveBoardList();

    RetrieveBoardResponseDto retrieveBoard(Long id);

    void deleteBoard(Long id);

    UpdateBoardResponseDto updateBoard(Long id, UpdateBoardRequestDto boardRequestDto);
}