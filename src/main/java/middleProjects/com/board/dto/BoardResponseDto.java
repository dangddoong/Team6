package middleProjects.com.board.dto;

import lombok.Getter;
import middleProjects.com.board.entity.Board;

@Getter
public class BoardResponseDto {

    private final Long id;
    private final String title;
    private final String content;

    public BoardResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    public static BoardResponseDto of(Board board){
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent());
    }

}
