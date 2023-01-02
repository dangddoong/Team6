package middleProjects.com.dto.member.register;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import middleProjects.com.entity.Member;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class RegisterResponseDto {

    private final String username;
    private final String accessToken;
    private final String refreshToken;
    private final String role;

    public RegisterResponseDto(Member member, String accessToken){
        this.username = member.getUsername();
        this.accessToken = accessToken;
        this.refreshToken = member.getRefreshToken();
        this.role = member.getRole().toString();
    }

    public static  RegisterResponseDto of(Member member, String accessToken){
        return new RegisterResponseDto(member,accessToken);
    }

}
