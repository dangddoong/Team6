package middleProjects.com.member.dto.info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import middleProjects.com.member.entity.Member;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ResponseDto {

    private final String username; // 사용자 id
    private final String name; // 사용자 이름
    private final String role;  // 사용자 role

    public ResponseDto(Member member){
        this.username = member.getUsername();
        this.name = member.getName();
        this.role = member.getRole().toString();
    }

    public static ResponseDto of(Member member){
        return new ResponseDto(member);
    }


}
