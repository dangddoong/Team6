package middleProjects.com.dto.board;

import lombok.Getter;
import middleProjects.com.dto.comment.CommentResponseDto;
import middleProjects.com.entity.Board;
import middleProjects.com.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RetrieveBoardResponseDto {

    private final Long id;
    private final String title;
    private final String username;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;
//    private final List<CommentResponseDto> comments;

    private final Long recommendCount;

    public RetrieveBoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getMember().getUsername();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.modDate = board.getModDate();
//        this.comments = board.getComments();
        this.recommendCount = board.getRecommendCount();
    }



}
