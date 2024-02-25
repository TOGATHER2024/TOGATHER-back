package jusomejusome.togather.category.domain;

import jakarta.persistence.*;
import jusomejusome.togather.calendar.domain.Calendar;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "calendar_id", referencedColumnName = "calendar_id") // calendar_id를 참조하는 외래키
    private Calendar calendar; // calendar 객체에 대한 참조

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;
}
