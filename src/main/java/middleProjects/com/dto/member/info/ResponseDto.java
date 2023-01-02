package middleProjects.com.dto.member.info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import middleProjects.com.entity.Member;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ResponseDto {

    private final String username;
    private final String name;
    private final String role;

    // 추가할 것 -> 사용자가 작성한 게시글, 좋아요 누른 게시글
//    private List<Comment>

    public ResponseDto(Member member){
        this.username = member.getUsername();
        this.name = member.getName();
        this.role = member.getRole().toString();
    }

    public static ResponseDto of(Member member){
        return new ResponseDto(member);
    }
}
