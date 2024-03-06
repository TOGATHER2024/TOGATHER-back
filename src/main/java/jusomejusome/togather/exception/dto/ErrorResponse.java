package jusomejusome.togather.exception.dto;

import jusomejusome.togather.exception.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String error;
    private String code;
    private String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(e.getHttpStatus().value())
                        .error(e.name())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }
}