package middleProjects.com.service.comment;

import middleProjects.com.dto.comment.CommentResponseDto;

public interface CommentService {
    CommentResponseDto createComment(Long boardId, String contents, String username);
    CommentResponseDto updateComment(Long commentId, String contents, String username);
    void deleteComment(Long commentId, String username);
}
