package middleProjects.com.board.dto;

import lombok.Getter;
import middleProjects.com.board.entity.Board;
import middleProjects.com.comment.dto.CommentResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetBoardResponseDto {
    private final Long id;
    private final String title;
    private final String username;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;
    private final Long recommendCount;

    public GetBoardResponseDto(Board board, Long boardRecommendCount) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getMember().getUsername();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.modDate = board.getModDate();
        this.recommendCount = boardRecommendCount;
    }
}
