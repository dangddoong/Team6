package middleProjects.com.dto.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import middleProjects.com.entity.Comment;

import java.time.LocalDateTime;

@Getter
//@RequiredArgsConstructor
public class CommentResponseDto {
    private final Long commentId;
    private final String comment;
    private final String commentWriter;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;
    private final Long recommendCount;

    public CommentResponseDto(Comment comment){
        this.commentId = comment.getId();
        this.comment = comment.getContents();
        this.commentWriter = comment.getMember().getUsername();
        this.createDate = comment.getCreateDate();
        this.modDate = comment.getModDate();
        this.recommendCount = comment.getRecommendCount();
    }
}
