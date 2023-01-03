package middleProjects.com.dto.member.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public final class LoginRegisterDto {

    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]*$")
    private final String username;

    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private final String password;


}
