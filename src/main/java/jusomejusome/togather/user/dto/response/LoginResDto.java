package jusomejusome.togather.user.dto.response;

import jusomejusome.togather.user.domain.User;
import lombok.*;

@Getter
public class LoginResDto {
    private Long userId;
    private String email;
    private String accessToken;

    @Builder
    public LoginResDto(User user, String accessToken){
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.accessToken = accessToken;
    }
}