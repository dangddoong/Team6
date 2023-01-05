package middleProjects.com.board.service;

import lombok.RequiredArgsConstructor;
import middleProjects.com.board.dto.*;
import middleProjects.com.board.entity.Board;
import middleProjects.com.board.entity.BoardRecommendation;
import middleProjects.com.board.repository.BoardRecommendationRepository;
import middleProjects.com.board.repository.BoardRepository;
import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.comment.entity.Comment;
import middleProjects.com.comment.repository.CommentRecommendationRepository;
import middleProjects.com.comment.repository.CommentRepository;
import middleProjects.com.exception.CustomException;
import middleProjects.com.exception.ExceptionStatus;
import middleProjects.com.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final CommentRecommendationRepository commentRecommendationRepository;
    private final BoardRecommendationRepository boardRecommendationRepository;

    //게시글 생성
    @Transactional
    public CreateBoardResponseDto createBoard(CreateBoardRequestDto createBoardRequestDto, Member member) {
        Board board = new Board(createBoardRequestDto.getTitle(), createBoardRequestDto.getContent(), member);
        boardRepository.save(board);
        return new CreateBoardResponseDto(board);
    }

    //게시물 전체 조회
    @Transactional
    public List<RetrieveBoardResponseDto> retrieveBoardList() {
        // 게시물도 페이징(10개/수정일자 내림차순), 댓글도 페이징(10개/수정일자 내림차순)
        Page<Board> boardPage = boardRepository.findAll(pageableSetting(1));
        List<RetrieveBoardResponseDto> retrieveBoardResponseDtoList = new ArrayList<>();

        for (Board board : boardPage) {
            Page<Comment> commentPage = commentRepository.findAllByBoardId(board.getId(), pageableSetting(1));
            List<CommentResponseDto> commentList = new ArrayList<>();
            for (Comment comment : commentPage) {
                Long commentRecommendCount = commentRecommendationRepository.countByComment(comment);
                commentList.add(new CommentResponseDto(comment, commentRecommendCount));
            }
            Long recommendCount = boardRecommendationRepository.countByBoardId(board.getId());
            retrieveBoardResponseDtoList.add(new RetrieveBoardResponseDto(board, recommendCount, commentList));
        }
        return retrieveBoardResponseDtoList;
    }

    @Transactional
    public List<GetBoardResponseDto> getBoardListToPagination(int pageChoice){
        if(pageChoice < 1) { throw new IllegalArgumentException("잘못된 페이지 접근입니다.");}
        Page<Board> boardPage = boardRepository.findAll(pageableSetting(pageChoice));
        if(boardPage.isEmpty()) {
            throw new IllegalArgumentException("요청하신 페이지가 없습니다.");
        }
        List<GetBoardResponseDto> getBoardResponseDtoList = new ArrayList<>();
        for(Board board: boardPage){
            Long recommendCount = boardRecommendationRepository.countByBoardId(board.getId());
            getBoardResponseDtoList.add(new GetBoardResponseDto(board, recommendCount));
        }
        return getBoardResponseDtoList;
    }


    //게시물 하나 조회
    @Transactional
    public RetrieveBoardResponseDto retrieveBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_IS_NOT_EXIST));

        Page<Comment> commentPage = commentRepository.findAllByBoardId(boardId, pageableSetting(1));
        List<CommentResponseDto> commentList = new ArrayList<>();
        for (Comment comment : commentPage) {
            Long commentRecommendCount = commentRecommendationRepository.countByComment(comment);
            commentList.add(new CommentResponseDto(comment, commentRecommendCount));
        }
        Long recommendCount = boardRecommendationRepository.countByBoardId(boardId);
        // 게시물좋아요레포에서 CountBy로 게시물 좋아요 가져온다.
        return new RetrieveBoardResponseDto(board, recommendCount, commentList);
    }

    //게시물 삭제
    @Transactional
    public void deleteBoard(Long boardId, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_IS_NOT_EXIST));
        board.checkUser(board, member);
        List<Comment> commentList =commentRepository.findAllByBoard(board);
        for(Comment comment : commentList) {
            commentRecommendationRepository.deleteAllByCommentId(comment.getId());
        }
        boardRecommendationRepository.deleteByBoard(board);
        boardRepository.deleteById(boardId);

    }

    //게시물 수정
    @Transactional
    public UpdateBoardResponseDto updateBoard(Long boardId, UpdateBoardRequestDto boardRequestDto, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_IS_NOT_EXIST));
        board.checkUser(board, member);
        board.updateBoard(boardRequestDto);
        boardRepository.save(board);
        Long boardRecommendCount = boardRecommendationRepository.countByBoardId(boardId);
        return new UpdateBoardResponseDto(board, boardRecommendCount);
    }


    @Override
    @Transactional
    public void recommendBoard(Long boardId, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_IS_NOT_EXIST));
        Optional<BoardRecommendation> optionalBoardRecommend = boardRecommendationRepository.findByMemberAndBoardId(member, boardId);
        if (optionalBoardRecommend.isPresent()) {
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }
        BoardRecommendation boardRecommendation = new BoardRecommendation(board, member);
        boardRecommendationRepository.save(boardRecommendation);
    }
    @Transactional
    @Override
    public void unRecommendBoard(Long boardId, Member member){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_IS_NOT_EXIST));
        Optional<BoardRecommendation> optionalBoardRecommend = boardRecommendationRepository.findByMemberAndBoardId(member, boardId);
        if (!optionalBoardRecommend.isPresent()) {
            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
        }
        boardRecommendationRepository.delete(optionalBoardRecommend.get());
    }

    public Pageable pageableSetting(int pageChoice) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modDate");
        Pageable pageable = PageRequest.of(pageChoice-1, 10, sort);
        return pageable;
    }
}