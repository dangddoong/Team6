package middleProjects.com.board.dto;

import lombok.Getter;
import middleProjects.com.comment.dto.CommentResponseDto;
import middleProjects.com.board.entity.Board;

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

    public RetrieveBoardResponseDto(Board board, Long boardRecommendCount, List<CommentResponseDto> commentResponseDtoList) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getMember().getUsername();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.modDate = board.getModDate();
        this.comments = commentResponseDtoList;
        this.recommendCount = boardRecommendCount;
    }



}
