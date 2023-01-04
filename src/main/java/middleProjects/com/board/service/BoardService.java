package middleProjects.com.board.service;


import middleProjects.com.board.dto.*;
import middleProjects.com.member.entity.Member;

import java.util.List;


public interface BoardService {

    CreateBoardResponseDto createBoard(CreateBoardRequestDto createBoardRequestDto, Member Member);

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

    void recommendBoard(Long postId, Member member);

    void unRecommendBoard(Long boardId, Member member);

}