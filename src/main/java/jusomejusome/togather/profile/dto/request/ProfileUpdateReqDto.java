package jusomejusome.togather.profile.dto.request;

import lombok.Getter;

@Getter
public class ProfileUpdateReqDto {
    private String nickname;
    private String profileImageUrl;
    private String job;
    private String introduce;
    private String website;
    private String organization;
}
