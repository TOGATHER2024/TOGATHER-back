package jusomejusome.togather.user.service;

import jusomejusome.togather.exception.custom.CustomException;
import jusomejusome.togather.exception.type.ErrorCode;
import jusomejusome.togather.user.domain.User;
import jusomejusome.togather.user.dto.request.SignUpReqDto;
import jusomejusome.togather.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long signUp(SignUpReqDto signUpReqDto) {
        if (existsByEmail(signUpReqDto.getEmail())) {
            throw new CustomException(ErrorCode.USER_EXISTED_EXCEPTION);
        }
        User user = userRepository.save(User.builder()
                .email(signUpReqDto.getEmail())
                .phone(signUpReqDto.getPhone())
                .password(passwordEncoder.encode(signUpReqDto.getPassword()))
                .build());
        return user.getUserId();
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND_EXCEPTION));
    }

}
