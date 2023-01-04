package middleProjects.com.comment.controller;

import lombok.RequiredArgsConstructor;
import middleProjects.com.comment.dto.CommentRequestDto;
import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.comment.dto.CreateCommentResponseDto;
import middleProjects.com.security.SecurityUtil;
import middleProjects.com.comment.service.CommentServiceImpl;
import middleProjects.com.security.members.MemberDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;

    @PostMapping("/comments/{boardId}")
    public CreateCommentResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {

        String contents = commentRequestDto.getComment();

        return commentServiceImpl.createComment(boardId, contents, memberDetails.getMember());
    }

    @PutMapping("/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        String contents = commentRequestDto.getComment();
        return commentServiceImpl.updateComment(commentId, contents, memberDetails.getMember());
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetails memberDetails) {
        commentServiceImpl.deleteComment(commentId, memberDetails.getMember());
        return new ResponseEntity<>("댓글 삭제완료", HttpStatus.OK);
    }


    @PostMapping("/comments/{commentId}/recommendation/")
    public ResponseEntity<String> recommendComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetails memberDetails) {
        commentServiceImpl.recommendComment(commentId, memberDetails.getMember());
        return new ResponseEntity<>("댓글 좋아요 완료", HttpStatus.CREATED);
    }

    @PostMapping("/comments/{commentId}/unRecommendation/")
    public ResponseEntity<String> unRecommendComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetails memberDetails) {
        commentServiceImpl.unRecommendComment(commentId, memberDetails.getMember());
        return new ResponseEntity<>("댓글 좋아요 취소완료", HttpStatus.OK);
    }
    //     TODO: admin URL에 대해서는 논의가 필요합니다.
//    @PutMapping("/admin/comments/{commentId}")
//    public ResponseEntity adminUpdateComment(@PathVariable Long commentId,@RequestBody CommentRequestDto commentRequestDto) {
//       commentService.update(id, dto);
//     return ResponseEntity.ok(id);
//        String comment = commentRequestDto.getComment();
//        return commentServiceImpl.adminUpdateComment(commentId, comment);
//    }

}
