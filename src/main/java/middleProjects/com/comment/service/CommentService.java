package middleProjects.com.comment.service;

import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.comment.dto.CreateCommentResponseDto;
import middleProjects.com.member.entity.Member;

public interface CommentService {
    CreateCommentResponseDto createComment(Long boardId, String contents, String username);
    CommentResponseDto updateComment(Long commentId, String contents, String username);
    void deleteComment(Long commentId, String username);
    void recommendComment(Long commentId, Member member);
    void unRecommendComment(Long commentId, Member member);}
