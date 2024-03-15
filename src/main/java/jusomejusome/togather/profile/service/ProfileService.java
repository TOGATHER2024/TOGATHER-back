package jusomejusome.togather.profile.service;

import jusomejusome.togather.exception.custom.CustomException;
import jusomejusome.togather.exception.type.ErrorCode;
import jusomejusome.togather.profile.domain.Profile;
import jusomejusome.togather.profile.dto.request.ProfileUpdateReqDto;
import jusomejusome.togather.profile.dto.response.ProfileResDto;
import jusomejusome.togather.profile.repository.ProfileRepository;
import jusomejusome.togather.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
@Transactional
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileResDto createProfile(Long calendarId, User user) {
        Profile profile = profileRepository.save(Profile.builder()
                .user(user)
                .mainCalendarId(calendarId)
                .nickname(user.getPhone().substring(user.getPhone().length() - 4)) //임시로 전화번호 뒤 4자리를 닉네임으로 설정
                .build());
        return findProfileById(profile.getProfileId());
    }

    @Transactional(readOnly = true)
    public ProfileResDto findProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PROFILE_NOT_FOUND_EXCEPTION));
        return ProfileResDto.from(profile);
    }

    public void deleteProfile(Long id, User user) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PROFILE_NOT_FOUND_EXCEPTION));
        if(!profile.isAuthorizedUser(user)) {
            throw new CustomException(ErrorCode.NO_AUTHORITY_PROFILE_REMOVE);
        }
        profileRepository.delete(profile);
    }

    public ProfileResDto updateProfile(Long id, ProfileUpdateReqDto profileUpdateReqDto, User user) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PROFILE_NOT_FOUND_EXCEPTION));
        if(!profile.isAuthorizedUser(user)) {
            throw new CustomException(ErrorCode.NO_AUTHORITY_PROFILE_UPDATE);
        }
        profile.update(profileUpdateReqDto.getNickname(), profileUpdateReqDto.getProfileImageUrl(), profileUpdateReqDto.getJob(),
                profileUpdateReqDto.getIntroduce(), profileUpdateReqDto.getWebsite(), profileUpdateReqDto.getOrganization());
        return ProfileResDto.from(profile);
    }

}
