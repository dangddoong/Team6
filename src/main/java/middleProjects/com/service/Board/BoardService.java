package middleProjects.com.service.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import middleProjects.com.dto.board.*;
import middleProjects.com.entity.Board;
import middleProjects.com.entity.Member;
import middleProjects.com.repository.BoardRepository;
import middleProjects.com.repository.MemberRepository;
import middleProjects.com.security.SecurityUtil;
import middleProjects.com.security.members.MemberDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createBoard(CreateBoardRequestDto createBoardRequestDto, Member member) {
        Board board = new Board(createBoardRequestDto.getTitle(), createBoardRequestDto.getContent(), member);
        // 과연 이렇게 객체를 넘기는게 맞나?
        boardRepository.save(board);
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
        Board board = boardRepository.findById(id).orElseThrow(()-> new NullPointerException("게시글이 존재하지 않습니다."));
        return new RetrieveBoardResponseDto(board);
    }

    //게시물 삭제
    @Transactional
    public void deleteBoard(Long id) {
        String user = SecurityUtil.getCurrentMemberEmail();

        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("찾는 게시물이 존재하지 않습니다."));
        boardRepository.deleteById(id);
    }

    //게시물 수정
    @Transactional
    public UpdateBoardResponseDto updateBoard(Long id, UpdateBoardRequestDto boardRequestDto) {
        String user = SecurityUtil.getCurrentMemberEmail();
        Member member = memberRepository.findByUsername(user).orElseThrow(IllegalArgumentException::new);
        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("찾는 게시물이 존재하지 않습니다."));
        board.updateBoard(boardRequestDto);
        boardRepository.save(board);
        return new UpdateBoardResponseDto(board);
    }

}
