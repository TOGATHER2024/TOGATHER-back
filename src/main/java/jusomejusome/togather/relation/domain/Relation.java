package jusomejusome.togather.relation.domain;

import jakarta.persistence.*;
import jusomejusome.togather.Role.domain.Role;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "relation")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id", nullable = false)
    private Long relationId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile1; // 첫 번째 Profile 엔티티 참조

    @ManyToOne
    @JoinColumn(name = "profile_id2", nullable = false)
    private Profile profile2; // 두 번째 Profile 엔티티 참조
}