package middleProjects.com.dto.comment;

import lombok.Getter;
import middleProjects.com.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long commentId;
    private final String comment;
    private final String commentWriter;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;
    private final Long recommendCount;

    CommentResponseDto(Comment comment, String commentWriter){
        this.commentId = comment.getId();
        this.comment = comment.getComment();
        this.commentWriter = commentWriter;
        this.createDate = comment.getCreateDate();
        this.modDate = comment.getModDate();
        this.recommendCount = comment.getRecommendCount();
    }
}
