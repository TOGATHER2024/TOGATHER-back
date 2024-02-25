package jusomejusome.togather.notification.domain;

import jakarta.persistence.*;
import jusomejusome.togather.global.BaseTimeEntity;
import jusomejusome.togather.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", updatable = false)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false, updatable = false)
    private Profile receiver;

    @Column(nullable = false)
    private String content;

    private String detail;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType; //Event/약속/(댓글)/팀캘린더초대/

    @Column(name = "content_id", nullable = false)
    private Long contentId;

}