package jusomejusome.togather.eventRepeat.domain;

import jakarta.persistence.*;
import jusomejusome.togather.calendar.domain.Calendar;
import jusomejusome.togather.event.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EventRepeat")
public class EventRepeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_repeat_id", columnDefinition = "BINARY(16)", nullable = false)
    private Long eventRepeatId;


    // 여기서는 Event 엔티티에 대한 참조를 유지합니다.
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
    private Calendar calendar;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "end", nullable = false)
    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    @Column(name = "repeat_type", nullable = false)
    private RepeatType repeatType;
}
