package middleProjects.com.comment.dto;

import lombok.Getter;
import middleProjects.com.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
//@RequiredArgsConstructor
//@NoArgsConstructor(force=true)
public class CommentResponseDto {
    private final Long boardId;
    private final Long commentId;
    private final String comment;
    private final String commentWriter;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;
    private final Long recommendCount;

    public CommentResponseDto(Comment comment, Long recommendCount){
        this.boardId = comment.getBoard().getId();
        this.commentId = comment.getId();
        this.comment = comment.getContents();
        this.commentWriter = comment.getMember().getUsername();
        this.createDate = comment.getCreateDate();
        this.modDate = comment.getModDate();
        this.recommendCount = recommendCount;
    }

}
