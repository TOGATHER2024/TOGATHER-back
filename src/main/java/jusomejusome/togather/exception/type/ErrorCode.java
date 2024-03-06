package jusomejusome.togather.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //서버 관련 오류 - C0***
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "C0000", "알 수 없는 서버 에러가 발생했습니다."),

    //유저 관련 오류 - C1***
    USER_EXISTED_EXCEPTION(HttpStatus.BAD_REQUEST, "C1000", "이미 존재하는 email입니다."),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "C1001", "해당 유저 정보를 찾을 수 없습니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
