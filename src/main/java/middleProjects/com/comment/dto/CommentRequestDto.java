package middleProjects.com.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.beans.ConstructorProperties;

@Getter
public class CommentRequestDto {
    private final String comment;

    @ConstructorProperties({"comment"})
    public CommentRequestDto( String comment) {
        this.comment = comment;
    }
}
