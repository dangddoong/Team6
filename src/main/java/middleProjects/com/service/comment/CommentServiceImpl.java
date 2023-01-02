package middleProjects.com.service.comment;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.comment.CommentResponseDto;
import middleProjects.com.entity.Comment;
import middleProjects.com.entity.Member;
import middleProjects.com.repository.BoardRepository;
import middleProjects.com.repository.CommentRepository;
import middleProjects.com.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import middleProjects.com.entity.Board;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
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
}
