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
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "C1002", "해당 유저 정보를 찾을 수 없습니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
