package middleProjects.com.exception.controller;

import lombok.extern.slf4j.Slf4j;
import middleProjects.com.exception.CustomException;
import middleProjects.com.exception.ExceptionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    //    @ApiOperation(value = "인증 실패", notes = "인증 실패에 따른 예외가 발생했습니다.")
    @GetMapping(value = "/entry")
    public void EntryPointException() {
        throw new CustomException(ExceptionStatus.AUTHENTICATION_EXCEPTION);
    }

    //    @ApiOperation(value = "인가 거부", notes = "인가에 따른 예외가 발생했습니다.")
    @GetMapping(value = "/denied")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void AccessDeniedException() {
        throw new CustomException(ExceptionStatus.ACCESSDENIED_EXCEPTION);
    }
}