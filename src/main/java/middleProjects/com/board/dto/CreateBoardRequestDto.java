package middleProjects.com.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBoardRequestDto {
    private  final String title;
    private  final String content;

//    public CreateBoardRequestDto(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
    //    public CreateBoardRequestDto(@JsonProperty("title") String title, @JsonProperty("content") String content) {
//        this.title = title;
//        this.content = content;
//    }
}
