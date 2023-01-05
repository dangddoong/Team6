package middleProjects.com.comment.dto;

import lombok.Getter;

@Getter
public class ReplyRequestDto {

    private final String comment;

    public ReplyRequestDto(String comment) {
        this.comment = comment;
    }
}
