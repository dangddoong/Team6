package middleProjects.com.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import middleProjects.com.comment.entity.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReplyResponseDto {

    private final String comment;
    private final String commentWriter;
    private final LocalDateTime createDate;
    private final List<ReplyResponseDto> replies = new ArrayList<>();


    public ReplyResponseDto(Comment comment, List<ReplyResponseDto> replyList){
        this.comment = comment.getContents();
        this.commentWriter = comment.getMember().getUsername();
        this.createDate  = comment.getCreateDate();
    }

    public static ReplyResponseDto of(Comment comment, List<ReplyResponseDto> replyList){
        return new ReplyResponseDto(comment,replyList);
    }
}
