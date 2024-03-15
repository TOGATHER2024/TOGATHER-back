package jusomejusome.togather.profile.dto.response;

import jusomejusome.togather.profile.domain.Profile;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResDto {
    private Long profileId;
    private Long userId;
    private Long mainCalendarId;
    private String nickname;
    private String profileImageUrl;
    private String job;
    private String introduce;
    private String website;
    private String organization;

    public static ProfileResDto from(Profile profile) {
        return new ProfileResDto(profile.getProfileId(),
                profile.getUser().getUserId(),
                profile.getMainCalendarId(),
                profile.getNickname(),
                profile.getProfileImageUrl(),
                profile.getJob(),
                profile.getIntroduce(),
                profile.getWebsite(),
                profile.getOrganization());
    }
}