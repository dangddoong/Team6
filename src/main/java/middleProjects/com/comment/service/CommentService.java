package middleProjects.com.comment.service;

import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.comment.dto.CreateCommentResponseDto;

public interface CommentService {
    CreateCommentResponseDto createComment(Long boardId, String contents, String username);
    CommentResponseDto updateComment(Long commentId, String contents, String username);
    void deleteComment(Long commentId, String username);
    void recommendComment(Long commentId, String username);
    void unRecommendComment(Long commentId, String username);}
