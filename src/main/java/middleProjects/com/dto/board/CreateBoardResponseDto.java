package middleProjects.com.dto.board;

import lombok.Getter;
import middleProjects.com.entity.Board;

import java.time.LocalDateTime;

@Getter
public class CreateBoardResponseDto {

    private final Long id;
    private final String title;
    private final String username;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;

    private final Long recommendCount;

    public CreateBoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getMember().getUsername();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.modDate = board.getModDate();
        this.recommendCount = board.getRecommendCount();
    }
}
