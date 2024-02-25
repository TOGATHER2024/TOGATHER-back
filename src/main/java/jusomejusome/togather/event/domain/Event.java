package jusomejusome.togather.event.domain;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jusomejusome.togather.calendar.domain.Calendar;
import jusomejusome.togather.category.domain.Category;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
    private Calendar calendar;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile; // Profile 엔티티 참조

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private EventType type; // Custom Enum for event types

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "is_repeating", nullable = false)
    private boolean isRepeating;

    //remind가 LocalDateTime -> 프론트에서 작업 처리
    //30분 전, 한 시간 전, 당일 오전 9:00 enum으로 처리 ->
    @Column(name = "remind_type", nullable = false)
    private RemindType remindType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", columnDefinition = "TEXT")
    private String location;

    @Column(name = "is_allDay", nullable = false)
    private boolean isAllDay;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "secret_level", nullable = false)
    private SecurityLevel secretLevel;
}