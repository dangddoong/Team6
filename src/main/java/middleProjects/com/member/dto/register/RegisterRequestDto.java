package middleProjects.com.member.dto.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import middleProjects.com.member.entity.Member;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterRequestDto {

    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]*$")
    private final String username;

    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private final String password;

    private final String nickName;

    private final String name;

    public Member toEntity(String password){
        return Member.builder()
                .username(this.getUsername())
                .name(this.getName())
                .nickName(this.getNickName())
                .password(password)
                .build();
    }


}
