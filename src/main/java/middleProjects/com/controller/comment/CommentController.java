package middleProjects.com.controller.comment;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.comment.CommentRequestDto;
import middleProjects.com.dto.comment.CommentResponseDto;
import middleProjects.com.security.SecurityUtil;
import middleProjects.com.service.comment.CommentService;
import middleProjects.com.service.comment.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;

    @PostMapping("/comments/{boardId}")
    public CommentResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto){
        String username = SecurityUtil.getCurrentMemberEmail();
        String contents = commentRequestDto.getComment();
        return commentServiceImpl.createComment(boardId, contents, username);
    }
    @PutMapping("/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId,@RequestBody CommentRequestDto commentRequestDto) {
//        commentService.update(id, dto);
//        return ResponseEntity.ok(id);
        String username = SecurityUtil.getCurrentMemberEmail();
        String contents = commentRequestDto.getComment();
        return commentServiceImpl.updateComment(commentId, contents, username);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
//        commentService.delete(id);
//        return ResponseEntity.ok(id);
        String username = SecurityUtil.getCurrentMemberEmail();
        commentServiceImpl.deleteComment(commentId, username);
        return new ResponseEntity<>("댓글 삭제완료", HttpStatus.OK);
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
