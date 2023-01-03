package middleProjects.com.service.Board;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.board.*;
import middleProjects.com.entity.Board;
import middleProjects.com.entity.Member;
import middleProjects.com.repository.BoardRepository;
import middleProjects.com.repository.MemberRepository;
import middleProjects.com.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //게시글 생성
    @Transactional
    public CreateBoardResponseDto createBoard(CreateBoardRequestDto createBoardRequestDto) {
        String user = SecurityUtil.getCurrentMemberEmail();
        Member member = memberRepository.findByUsername(user).orElseThrow(IllegalArgumentException::new);
        Board board = new Board(createBoardRequestDto.getTitle(), createBoardRequestDto.getContent(), member);
        boardRepository.save(board);
        return new CreateBoardResponseDto(board);
    }

    //게시물 전체 조회
    @Transactional
    public List<RetrieveBoardResponseDto> retrieveBoardList() {
        List<Board> boardList = boardRepository.findAllByOrderByModDateDesc();
        List<RetrieveBoardResponseDto> retrieveBoardResponseDtoList = new ArrayList<>();
        for(Board board: boardList) {
            retrieveBoardResponseDtoList.add(new RetrieveBoardResponseDto(board));
        }
        return retrieveBoardResponseDtoList;
    }

    //게시물 하나 조회
    @Transactional
    public RetrieveBoardResponseDto retrieveBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return new RetrieveBoardResponseDto(board);
    }

    //게시물 삭제
    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("찾는 게시물이 존재하지 않습니다."));
        String user = SecurityUtil.getCurrentMemberEmail();
        board.checkUser(board,user);
        boardRepository.deleteById(id);
    }

    //게시물 수정
    @Transactional
    public UpdateBoardResponseDto updateBoard(Long id, UpdateBoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("찾는 게시물이 존재하지 않습니다."));
        String user = SecurityUtil.getCurrentMemberEmail();
        board.checkUser(board,user);
        board.updateBoard(boardRequestDto);
        boardRepository.save(board);
        return new UpdateBoardResponseDto(board);
    }

}