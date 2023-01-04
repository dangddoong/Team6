package middleProjects.com.comment.service;

import middleProjects.com.comment.dto.CommentResponseDto;

public interface CommentService {
    CommentResponseDto createComment(Long boardId, String contents, String username);
    CommentResponseDto updateComment(Long commentId, String contents, String username);
    void deleteComment(Long commentId, String username);
    String recommendComment(Long commentId, String username);
}
