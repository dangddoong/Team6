package middleProjects.com.exception.member;

public class MemberNicknameException extends RuntimeException {

    public MemberNicknameException() {
        super();
    }

    public MemberNicknameException(String message) {
        super(message);
    }

    public MemberNicknameException(String message, Throwable cause) {
        super(message, cause);
    }
}
