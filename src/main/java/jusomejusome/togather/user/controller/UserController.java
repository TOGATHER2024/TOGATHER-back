package jusomejusome.togather.user.controller;

import jakarta.validation.Valid;
import jusomejusome.togather.user.domain.User;
import jusomejusome.togather.user.dto.request.SignUpReqDto;
import jusomejusome.togather.user.dto.response.UserResDto;
import jusomejusome.togather.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResDto signUp(@RequestBody @Valid SignUpReqDto signUpReqDto) {
        Long id = userService.signUp(signUpReqDto);
        User user = userService.findUserById(id);
        return UserResDto.from(user);
    }
}
