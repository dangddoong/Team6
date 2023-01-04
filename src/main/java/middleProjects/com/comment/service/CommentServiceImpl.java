package middleProjects.com.comment.service;

import lombok.RequiredArgsConstructor;
import middleProjects.com.comment.dto.CommentResponseDto;
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
    public CommentResponseDto createComment(Long boardId, String contents, String username) {
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        Member member = memberRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        Comment comment = new Comment(contents, board, member);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    @Override
    public CommentResponseDto updateComment(Long commentId, String contents, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
        comment.memberAndCommentWriterEqualCheck(username);
        comment.updateComment(contents);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
        comment.memberAndCommentWriterEqualCheck(username);
        commentRepository.deleteById(commentId);
    }

    @Transactional
    @Override
    public String recommendComment(Long commentId, String username) {
//        Member member = memberRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
        Optional<CommentRecommendation> optionalCommentRecommend = commentRecommendationRepository.findByMemberAndCommentId(comment.getMember(), commentId);
        if(optionalCommentRecommend.isPresent()){
            commentRecommendationRepository.delete(optionalCommentRecommend.get());
            return "댓글 좋아요 취소완료" ;
        }

        CommentRecommendation commentRecommend = new CommentRecommendation(comment, comment.getMember());
        commentRecommendationRepository.save(commentRecommend);
        return "댓글 좋아요 완료";
    }
    // 이런식으로 findBy 메서드 만들어서 코드 정리를 해볼게요.(성현)s
//    private Board findBoardByBoardId(Long boardId){
//        return boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
//    }
}

