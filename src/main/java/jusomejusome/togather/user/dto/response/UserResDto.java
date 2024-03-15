package jusomejusome.togather.user.dto.response;

import jusomejusome.togather.user.domain.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResDto {
    private Long userId;
    private String email;
    private String phone;

    public static UserResDto from(User user) {
        return new UserResDto(user.getUserId(),
                user.getEmail(),
                user.getPhone());
    }
}
