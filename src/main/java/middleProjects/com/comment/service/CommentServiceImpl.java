package middleProjects.com.comment.service;

import lombok.RequiredArgsConstructor;
import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.comment.dto.CreateCommentResponseDto;
import middleProjects.com.comment.entity.Comment;
import middleProjects.com.comment.entity.CommentRecommendation;
import middleProjects.com.exception.CustomException;
import middleProjects.com.exception.ExceptionStatus;
import middleProjects.com.member.entity.Member;
import middleProjects.com.board.repository.BoardRepository;
import middleProjects.com.comment.repository.CommentRecommendationRepository;
import middleProjects.com.comment.repository.CommentRepository;
import middleProjects.com.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import middleProjects.com.board.entity.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRecommendationRepository commentRecommendationRepository;

    @Transactional
    @Override
    public CreateCommentResponseDto createComment(Long boardId, String contents, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_IS_NOT_EXIST));
        Comment comment = new Comment(contents, board, member);
        commentRepository.save(comment);
        return new CreateCommentResponseDto(comment);
    }

    @Transactional
    @Override
    public List<CommentResponseDto> getCommentListToPagination(int pageChoice, Long boardId){
        Page<Comment> commentPage = commentRepository.findAllByBoardId(boardId, pageableSetting(pageChoice));
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment: commentPage){
            Long recommendCount = commentRecommendationRepository.countByComment(comment);
            commentResponseDtoList.add(new CommentResponseDto(comment, recommendCount));
        }
        return commentResponseDtoList;
    }

    @Transactional
    @Override
    public CommentResponseDto updateComment(Long commentId, String contents, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ExceptionStatus.COMMENT_IS_NOT_EXIST));
        comment.memberAndCommentWriterEqualCheck(member.getId());
        comment.updateComment(contents);
        commentRepository.save(comment);
        Long commentRecommendationCount = commentRecommendationRepository.countByComment(comment);
        return new CommentResponseDto(comment, commentRecommendationCount);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ExceptionStatus.COMMENT_IS_NOT_EXIST));
        comment.memberAndCommentWriterEqualCheck(member.getId());
        commentRepository.deleteById(commentId);
    }


    @Transactional
    @Override
    public void recommendComment(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ExceptionStatus.COMMENT_IS_NOT_EXIST));
        Optional<CommentRecommendation> optionalCommentRecommend = commentRecommendationRepository.findByMemberAndCommentId(member, commentId);
        if (optionalCommentRecommend.isPresent()) {
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }
        CommentRecommendation commentRecommend = new CommentRecommendation(comment, member);
        commentRecommendationRepository.save(commentRecommend);
    }

    @Transactional
    @Override
    public void unRecommendComment(Long commentId, Member member) {
        Optional<CommentRecommendation> optionalCommentRecommend = commentRecommendationRepository.findByMemberAndCommentId(member, commentId);
        if (!optionalCommentRecommend.isPresent()) {
            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
        }
        commentRecommendationRepository.delete(optionalCommentRecommend.get());
    }
    public Pageable pageableSetting(int pageChoice) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modDate");
        Pageable pageable = PageRequest.of(pageChoice-1, 10, sort);
        return pageable;
    }
}

