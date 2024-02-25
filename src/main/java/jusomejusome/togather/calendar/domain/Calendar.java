package jusomejusome.togather.calendar.domain;

import jakarta.persistence.*;
import jusomejusome.togather.profile.domain.Profile;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "calendar")
@Getter // Lombok 어노테이션으로 모든 필드에 대한 getter를 자동 생성
//@Setter를 사용할지 여부는 클래스의 불변성(immutable)을 원하는지에 따라 결정됩니다. 만약 객체가 생성된 후에 변경될 필요가 없다면, setter 메소드를 제공하지 않아도 됩니다.

//각 객체? ID 데이터형으로 UUID 쓸건지 혹은 long 파일 쓸건지
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id", updatable = false, nullable = false)
    private Long calendarId;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile; // Profile 엔티티 참조

    @Column(name = "name", nullable = false, length = 100)
    private String name;  //calendar name unique?

    //여기서 타입을 나누는 게 맞나?? 프론트에서 디자인 처리 할 때 나누는 거 아닌가?
    @Column(name = "type", nullable = false, length = 10)
    private String type;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    //이거 필요한가? 그냥 사용자 local data time 필요할 때 받아오는 게 낫지 않을까?
    @Column(name = "zone_id")
    private String zoneId;
}