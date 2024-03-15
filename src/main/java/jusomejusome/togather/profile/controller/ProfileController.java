package jusomejusome.togather.profile.controller;

import jusomejusome.togather.config.authentication.AuthUser;
import jusomejusome.togather.profile.dto.request.ProfileUpdateReqDto;
import jusomejusome.togather.profile.dto.response.ProfileResDto;
import jusomejusome.togather.profile.service.ProfileService;
import jusomejusome.togather.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/{calendarId}")
    public ProfileResDto createProfile(@PathVariable Long calendarId, @AuthUser User user) {
        return profileService.createProfile(calendarId, user);
    }

    @GetMapping("/{id}")
    public ProfileResDto getProfileById(@PathVariable Long id) {
        return profileService.findProfileById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id, @AuthUser User user) {
        profileService.deleteProfile(id, user);
        return new ResponseEntity<>("프로필이 삭제되었습니다. profileId = " + id, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ProfileResDto updateProfile(@PathVariable Long id, @RequestBody ProfileUpdateReqDto profileUpdateReqDto, @AuthUser User user){
        return profileService.updateProfile(id, profileUpdateReqDto, user);
    }

}
