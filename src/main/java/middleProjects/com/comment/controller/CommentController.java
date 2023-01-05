package middleProjects.com.comment.controller;

import lombok.RequiredArgsConstructor;
import middleProjects.com.comment.dto.*;
import middleProjects.com.comment.service.CommentService;
import middleProjects.com.comment.service.CommentServiceImpl;
import middleProjects.com.security.members.MemberDetails;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{boardId}")
    public CreateCommentResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        String contents = commentRequestDto.getComment();
        return commentService.createComment(boardId, contents, memberDetails.getMember());
    }

    @GetMapping("/comments/{boardId}/pagination/{pageChoice}")
    public List<CommentResponseDto> getCommentListToPagination(@PathVariable Long boardId, @PathVariable int pageChoice) {
        return commentService.getCommentListToPagination(pageChoice, boardId);
    }

    @PutMapping("/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        String contents = commentRequestDto.getComment();
        return commentService.updateComment(commentId, contents, memberDetails.getMember());
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetails memberDetails) {
        commentService.deleteComment(commentId, memberDetails.getMember());
        return new ResponseEntity<>("댓글 삭제완료", HttpStatus.OK);
    }


    @PostMapping("/comments/{commentId}/recommendation/")
    public ResponseEntity<String> recommendComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetails memberDetails) {
        commentService.recommendComment(commentId, memberDetails.getMember());
        return new ResponseEntity<>("댓글 좋아요 완료", HttpStatus.CREATED);
    }

    @PostMapping("/comments/{commentId}/unrecommendation/")
    public ResponseEntity<String> unrecommendComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetails memberDetails) {
        commentService.unRecommendComment(commentId, memberDetails.getMember());
        return new ResponseEntity<>("댓글 좋아요 취소완료", HttpStatus.OK);
    }

    @PostMapping("/comments/{commentId}/reply")
    public HttpStatus addReply(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetails memberDetails, @RequestBody ReplyRequestDto replyRequestDto) {
        commentService.addReply(commentId, memberDetails.getMember(), replyRequestDto.getComment());
        return HttpStatus.OK;
    }

    //    @PutMapping("/admin/comments/{commentId}")
//    public ResponseEntity adminUpdateComment(@PathVariable Long commentId,@RequestBody CommentRequestDto commentRequestDto) {
//       commentService.update(id, dto);
//     return ResponseEntity.ok(id);
//        String comment = commentRequestDto.getComment();
//        return commentServiceImpl.adminUpdateComment(commentId, comment);
//    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<ReplyResponseDto> getOne(@PathVariable Long commentId) {
        ReplyResponseDto data = commentService.getOne(commentId);
        return ResponseEntity.status(200).body(data);
    }

}
