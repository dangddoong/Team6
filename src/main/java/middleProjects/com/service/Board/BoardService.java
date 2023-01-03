package middleProjects.com.service.Board;


import middleProjects.com.dto.board.*;

import java.util.List;


public interface BoardService {

    CreateBoardResponseDto createBoard(CreateBoardRequestDto createBoardRequestDto);

//    @Transactional
//    public void createBoard(CreateBoardRequestDto createBoardRequestDto, Member member) {
//        Board board = new Board(createBoardRequestDto.getTitle(), createBoardRequestDto.getContent(), member);
//        // 과연 이렇게 객체를 넘기는게 맞나?
//        boardRepository.save(board);
//    }

    List<RetrieveBoardResponseDto> retrieveBoardList();


    RetrieveBoardResponseDto retrieveBoard(Long id);

    void deleteBoard(Long id);

    UpdateBoardResponseDto updateBoard(Long id, UpdateBoardRequestDto boardRequestDto);

}