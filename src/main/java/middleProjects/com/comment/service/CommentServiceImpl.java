package middleProjects.com.comment.service;

import lombok.RequiredArgsConstructor;
import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.comment.dto.CreateCommentResponseDto;
import middleProjects.com.comment.entity.Comment;
import middleProjects.com.comment.entity.CommentRecommendation;
import middleProjects.com.member.entity.Member;
import middleProjects.com.board.repository.BoardRepository;
import middleProjects.com.comment.repository.CommentRecommendationRepository;
import middleProjects.com.comment.repository.CommentRepository;
import middleProjects.com.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import middleProjects.com.board.entity.Board;

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
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        Comment comment = new Comment(contents, board, member);
        commentRepository.save(comment);
        return new CreateCommentResponseDto(comment);
    }

    @Transactional
    @Override
    public CommentResponseDto updateComment(Long commentId, String contents, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
        comment.memberAndCommentWriterEqualCheck(member);
        comment.updateComment(contents);
        commentRepository.save(comment);
        Long commentRecommendationCount = commentRecommendationRepository.countByCommentId(commentId);
        return new CommentResponseDto(comment, commentRecommendationCount);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
        comment.memberAndCommentWriterEqualCheck(member);
        commentRepository.deleteById(commentId);
    }


    @Transactional
    @Override
    public void recommendComment(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
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
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
        Optional<CommentRecommendation> optionalCommentRecommend = commentRecommendationRepository.findByMemberAndCommentId(member, commentId);
        if (!optionalCommentRecommend.isPresent()) {
            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
        }
        commentRecommendationRepository.delete(optionalCommentRecommend.get());
    }
    // 이런식으로 findBy 메서드 만들어서 코드 정리를 해볼게요.(성현)s
//    private Board findBoardByBoardId(Long boardId){
//        return boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
//    }
}

