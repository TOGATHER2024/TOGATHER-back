package jusomejusome.togather.appointment.domain;

import jakarta.persistence.*;
import jusomejusome.togather.global.BaseTimeEntity;
import jusomejusome.togather.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Appointment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id", updatable = false)
    private Long appointmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile host;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "within_weeks", nullable = false)
    private Integer withinWeeks;

    @Column(name = "start_of_preferred_time", nullable = false)
    private LocalTime startOfPreferredTime; //선호 시간대

    @Column(name = "end_of_preferred_time", nullable = false)
    private LocalTime endOfPreferredTime;

    @Column(name = "num_of_slots", nullable = false)
    private Integer numOfSlots; //추천 슬롯 수

    @Column(name = "vote_deadline", nullable = false)
    private LocalDateTime voteDeadline;

    @Column(name = "minimum_time")
    private Integer minimumTime; //최소 만남 시간(정수로만 받기)

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_day")
    private PreferredDay preferredDay;

    @Column(name = "weather")
    private Boolean weather; //날씨 고려 여부

    @Column(name = "another_events")
    private Boolean anotherEvents; //전후일정 고려 여부
}
