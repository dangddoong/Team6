package middleProjects.com.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
public class CommentResponseDto {
    private final Long id;
    private final String comment;
    private final LocalDateTime createDate;
    private final LocalDateTime modDate;
}
