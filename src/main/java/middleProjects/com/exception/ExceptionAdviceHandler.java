package middleProjects.com.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionAdviceHandler {

    // 여기서 고민 1. 과연 아래처럼 하나하나 예외를 만들어서?
    // 혹은 성현님이 말씀해주셨던것처럼 custom 예외를 한 파일에?
//    @ExceptionHandler(MemberException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public CustomException MemberException() {
//        return new CustomException(ExceptionStatus.MEMBER_IS_EXIST);
//    }

    @ExceptionHandler({CustomException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity handleCustomException(CustomException ex) {
        return new ResponseEntity(new ErrorDto(ex.getExceptionStatus().getStatusCode(), ex.getExceptionStatus().getMessage()), HttpStatus.valueOf(ex.getExceptionStatus().getStatusCode()));
    }



//    @ExceptionHandler(MemberNicknameException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public CustomException MemberNicknameException() {
//        return new CustomException(ExceptionStatus.MEMBERNICKNAME_IS_EXIST);
//    }

}
