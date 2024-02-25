package jusomejusome.togather.Role.domain;

import jakarta.persistence.*;
import jusomejusome.togather.event.domain.SecurityLevel;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile; // Assuming there's a Profile entity defined elsewhere

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "security_level", nullable = false)
    private SecurityLevel securityLevel;
}

