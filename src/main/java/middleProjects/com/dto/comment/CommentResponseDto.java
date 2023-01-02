package middleProjects.com.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private  final Long id;
    private  final String comment;
    private  final LocalDateTime createDate;
    private  final LocalDateTime modDate;
}
