package jusomejusome.togather.config.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jusomejusome.togather.jwt.JwtTokenProvider;
import jusomejusome.togather.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AuthUser authUserAnnotation = parameter.getParameterAnnotation(AuthUser.class);
        assert authUserAnnotation != null;
        if(!authUserAnnotation.required()){
            throw new IllegalArgumentException("올바르지 않은 접근입니다. 헤더를 확인해주세요.");
        }

        Authentication authentication = jwtTokenProvider.getAuthenticationFromRequest(Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)));
        return (User) authentication.getPrincipal();
    }
}
