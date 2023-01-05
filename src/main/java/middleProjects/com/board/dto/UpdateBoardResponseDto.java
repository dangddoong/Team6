package middleProjects.com.board.dto;

import lombok.Getter;
import middleProjects.com.board.entity.Board;
import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.comment.entity.Comment;

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

    private final List<CommentResponseDto> commentList;

    public UpdateBoardResponseDto(Board board, Long recommendCount, List<CommentResponseDto> commentResponseDtoList) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getMember().getUsername();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.modDate = board.getModDate();
        this.recommendCount = recommendCount;
        this.commentList = commentResponseDtoList;
    }
}
