package middleProjects.com.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class ReplyRequestDto {
    private final String comment;
}
