package jusomejusome.togather.profile.repository;

import jusomejusome.togather.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
