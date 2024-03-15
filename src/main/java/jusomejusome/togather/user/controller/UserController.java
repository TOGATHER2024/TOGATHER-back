package jusomejusome.togather.user.controller;

import jakarta.validation.Valid;
import jusomejusome.togather.config.authentication.AuthUser;
import jusomejusome.togather.user.domain.User;
import jusomejusome.togather.user.dto.request.LoginReqDto;
import jusomejusome.togather.user.dto.request.SignUpReqDto;
import jusomejusome.togather.user.dto.response.LoginResDto;
import jusomejusome.togather.user.dto.response.UserResDto;
import jusomejusome.togather.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) {
        User user = userService.checkEmailAndPassword(loginReqDto);
        return userService.login(user);
    }

    @GetMapping("/test")
    public UserResDto test(@AuthUser User user){
        return UserResDto.from(user);
    }
}
