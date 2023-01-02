package middleProjects.com.dto.board;

import lombok.Getter;
import middleProjects.com.entity.Board;
import middleProjects.com.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UpdateBoardResponseDto {
    private final Long id;
    private final String title;
    private final String username;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;

    private final Long recommendCount;

    private final List<Comment> commentList;

    public UpdateBoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getMember().getUsername();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.modDate = board.getModDate();
        this.recommendCount = board.getRecommendCount();
        this.commentList = board.getComments();
    }
}
