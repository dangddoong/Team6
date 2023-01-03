package middleProjects.com.exception;

import lombok.Getter;

@Getter
public enum ExceptionStatus {

    MEMBER_IS_EXIST(409, " 이미 등록된 사용자 아이디입니다. "),
    MEMBERNICKNAME_IS_EXIST(409, " 이미 등록된 닉네임 입니다. ");


    ExceptionStatus (int code, String message) {
        this.statusCode = code;
        this.message = message;
    }

    private final int statusCode;
    private final String message;

}
