package jusomejusome.togather.user.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jusomejusome.togather.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpReqDto {
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^\\d{2,3}-?\\d{3,4}-?\\d{4}$",
            message = "유효하지 않은 전화번호 형식입니다.")
    private String phone;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!?,.@#$%^*+=-])[A-Za-z\\d!?,.@#$%^*+=-]{8,20}$",
            message = "8자 이상의 영문자 및 숫자와 특수문자(!?,.@#$%^*+=-)로 입력해주세요.")
    private String password;

    @Builder
    public SignUpReqDto(String email, String phone, String password) {
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .phone(this.phone)
                .password(this.password)
                .build();
    }


}
