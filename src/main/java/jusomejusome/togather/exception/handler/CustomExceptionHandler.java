package jusomejusome.togather.exception.handler;

import jusomejusome.togather.exception.custom.CustomException;
import jusomejusome.togather.exception.dto.ErrorResponse;
import jusomejusome.togather.exception.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    //전역 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> runtimeException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        log.error(String.format("UnHandled Exception : %s\n" + "%s:%s:%s", e, stackTrace[0].getClassName(),
                stackTrace[0].getMethodName(), stackTrace[0].getLineNumber()), e);
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error = HttpStatus.INTERNAL_SERVER_ERROR.toString();
        String message = ErrorCode.UNHANDLED_EXCEPTION.getMessage();
        String errorCode = ErrorCode.UNHANDLED_EXCEPTION.getCode();
        return ResponseEntity.internalServerError().body(new ErrorResponse(status, error, errorCode, message));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("error", HttpStatus.BAD_REQUEST);
        body.put("code", "C1000");
        String error = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        body.put("message", error);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
