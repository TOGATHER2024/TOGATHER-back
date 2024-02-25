package jusomejusome.togather.profile.domain;

import jakarta.persistence.*;
import jusomejusome.togather.global.BaseTimeEntity;
import jusomejusome.togather.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", updatable = false)
    private Long profileId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Long mainCalendarId;

    @Column(length = 30, nullable = false)
    private String nickname;

    @Column(columnDefinition = "TEXT")
    private String profileImageUrl;

    @Column(length = 100)
    private String job;

    @Column(columnDefinition = "TEXT")
    private String introduce; //한줄소개 추가할까?? ERD에는 반영안했음

    @Column(length = 500)
    private String website;

    @Column(length = 100)
    private String organization;

}
