package jusomejusome.togather.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //서버 관련 오류 - C0***
    UNHANDLED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "C0000", "알 수 없는 서버 에러가 발생했습니다."),

    //유저 관련 오류 - C1***
    //C1000: 회원 가입 시 발생하는 valid 관련 오류 exception
    USER_EXISTED_EXCEPTION(HttpStatus.BAD_REQUEST, "C1001", "이미 존재하는 email입니다."),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "C1002", "해당 유저 정보를 찾을 수 없습니다."),
    NO_USER_EXISTS_EXCEPTION(HttpStatus.NOT_FOUND, "C1003", "가입되지 않은 사용자입니다."),
    WRONG_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "C1004", "패스워드가 올바르지 않습니다."),
    EXPIRED_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "C1005", "토큰이 유효하지 않습니다."),
    INVALID_TOKEN_TYPE_EXCEPTION(HttpStatus.UNAUTHORIZED, "C1006", "토큰 타입이 올바르지 않습니다."),
    INVALID_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "C1007", "토큰이 유효하지 않습니다."),

    //프로필 관련 오류 - C2***
    PROFILE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "C2000", "해당 프로필 정보를 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
