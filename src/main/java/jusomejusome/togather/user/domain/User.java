package jusomejusome.togather.user.domain;

import jakarta.persistence.*;
import jusomejusome.togather.global.BaseTimeEntity;
import jusomejusome.togather.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long userId;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String phone;

    @Column(nullable = false) //길이 지정?
    private String password;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Profile profile;

    @Builder
    public User(Long userId, String email, String phone, String password) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}