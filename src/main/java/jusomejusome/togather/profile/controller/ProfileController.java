package jusomejusome.togather.profile.controller;

import jusomejusome.togather.config.authentication.AuthUser;
import jusomejusome.togather.profile.dto.response.ProfileResDto;
import jusomejusome.togather.profile.service.ProfileService;
import jusomejusome.togather.user.domain.User;
import lombok.RequiredArgsConstructor;
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
}
