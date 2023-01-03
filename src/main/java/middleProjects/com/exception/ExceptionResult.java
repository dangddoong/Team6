package middleProjects.com.exception;

import lombok.Getter;


@Getter
public class ExceptionResult {
    private int code;
    private String errorMessage;

    public ExceptionResult(ExceptionStatus exceptionStatus) {
        this.code = exceptionStatus.getStatusCode();
        this.errorMessage = exceptionStatus.getMessage();
    }
}
