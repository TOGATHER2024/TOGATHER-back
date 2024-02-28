package jusomejusome.togather.friend.domain;

import jakarta.persistence.*;
import jusomejusome.togather.global.BaseTimeEntity;
import jusomejusome.togather.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id", updatable = false)
    private Long friendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_profile", nullable = false)
    private Profile fromProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_profile", nullable = false)
    private Profile toProfile;

    //두 profile이 서로 친구를 맺으면 2개의 데이터 생성(from, to 각각 바꿔서)
}
