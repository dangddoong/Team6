package middleProjects.com.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionStatus {

    // 400 ->  BAD _ REQUEST : 잘못된 요청
    // 404 ->  NOT _ FOUND : 잘못된 리소스 접근
    // 409 ->  CONFLICT : 중복 데이터
    // 500 -> INTERNAL SERVER ERROR : 서버에러

    MEMBER_IS_EXIST(409, " 이미 등록된 사용자 아이디입니다. "),
    MEMBERNICKNAME_IS_EXIST(409, " 이미 등록된 닉네임 입니다. ");


    private final int statusCode;
    private final String message;

}
