package middleProjects.com.exception;


import lombok.RequiredArgsConstructor;
import middleProjects.com.entity.Member;
import middleProjects.com.exception.member.MemberException;
import middleProjects.com.exception.member.MemberNicknameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(MemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResult MemberException() {
        return new ExceptionResult(ExceptionStatus.MEMBER_IS_EXIST);
    }

    @ExceptionHandler(MemberNicknameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResult MemberNicknameException() {
        return new ExceptionResult(ExceptionStatus.MEMBERNICKNAME_IS_EXIST);
    }

}
