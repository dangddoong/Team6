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
    MEMBERNICKNAME_IS_EXIST(409, " 이미 등록된 닉네임 입니다. "),
    SERVER_ERROR(500, "관리자에게 문의 하세요"),
    COMMENT_IS_NOT_EXIST(404, " 댓글이 존재하지 않습니다. "),
    BOARD_IS_NOT_EXIST(404, " 게시물이 존재하지 않습니다. "),
    PAGINATION_IS_NOT_EXIST(404,"요청하신 페이지 내역이 존재하지 않습니다."),

    AUTHENTICATION_EXCEPTION(500, " 인증 실패에 따른 예외가 발생했습니다. "),
    ACCESSDENIED_EXCEPTION(500, " 인가에 따른 예외가 발생했습니다. "),
    MYMYMYMYMYMY(501, "MYMYMYMYMY");


    private final int statusCode;
    private final String message;

}
