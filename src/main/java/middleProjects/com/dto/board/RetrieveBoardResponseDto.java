package middleProjects.com.dto.board;

import lombok.Getter;
import middleProjects.com.dto.comment.CommentResponseDto;
import middleProjects.com.entity.Board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RetrieveBoardResponseDto {

    private final Long id;
    private final String title;
    private final String username;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;
    private final List<CommentResponseDto> comments;

    private final Long recommendCount;

    public RetrieveBoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getMember().getUsername();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.modDate = board.getModDate();
        this.comments = board.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.recommendCount = board.getRecommendCount();
    }



}
