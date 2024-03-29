package jusomejusome.togather.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jusomejusome.togather.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint { //인증에 실패할 경우 진행될 EntryPoint를 생성

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		// 이미 응답이 커밋된 경우, 더 이상 응답을 보내지 않는다.
		if (response.isCommitted()) {
			return;
		}
		log.error("Unauthorized Error : {}", authException.getMessage());
		// ErrorResponse 객체 생성
		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(HttpStatus.UNAUTHORIZED.value())
				.error(String.valueOf(HttpStatus.UNAUTHORIZED))
			.code("AUTH_ERROR")
			.message(authException.getMessage())
			.build();

		// JSON으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonResponse = objectMapper.writeValueAsString(errorResponse);

		// 응답 설정
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(jsonResponse);
	}


}

