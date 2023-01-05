package middleProjects.com.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import middleProjects.com.comment.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class jjCommentsResponseDto {

    private final Long commentId;
    private final String comment;
    private final String commentWriter;
    private final LocalDateTime createDate;
    private final List<Comment> commentList;


    public jjCommentsResponseDto(Comment comment, List<Comment> replyList){
        this.commentId = comment.getId();
        this.comment = comment.getContents();
        this.commentWriter = comment.getMember().getUsername();
        this.createDate  = comment.getCreateDate();
        this.commentList = replyList;
    }

    public static jjCommentsResponseDto of(Comment comment, List<Comment> replyList){
        return new jjCommentsResponseDto(comment,replyList);
    }
}
