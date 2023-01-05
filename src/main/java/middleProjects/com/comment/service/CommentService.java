package middleProjects.com.comment.service;

import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.comment.dto.CreateCommentResponseDto;
import middleProjects.com.comment.dto.ReplyRequestDto;
import middleProjects.com.comment.dto.jjCommentsResponseDto;
import middleProjects.com.member.entity.Member;

import java.util.List;

public interface CommentService {
    CreateCommentResponseDto createComment(Long boardId, String contents, Member member);

    List<CommentResponseDto> getCommentListToPagination(int pageChoice, Long boardId);
    CommentResponseDto updateComment(Long commentId, String contents, Member member);

    void deleteComment(Long commentId, Member member);

    void recommendComment(Long commentId, Member member);

    void unRecommendComment(Long commentId, Member member);

    void addReply(Long commentId, Member member, String contents);

//    jjCommentsResponseDto getOne(Long id);

    // 과연 이 대댓글은 새로운 entity를 만드는게 맞나? 혹은 parent, child를 이용해서 구현하는 것이 맞나?
}