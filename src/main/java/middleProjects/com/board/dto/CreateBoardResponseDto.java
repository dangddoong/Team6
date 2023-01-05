package middleProjects.com.board.dto;

import lombok.Getter;
import middleProjects.com.board.entity.Board;

import java.time.LocalDateTime;

@Getter
public class CreateBoardResponseDto {

    private final Long id;
    private final String title;
    private final String username;
    private final String content;
    private final LocalDateTime createDate;


    public CreateBoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getMember().getUsername();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
                                                                                                   
    }
}                                                          
