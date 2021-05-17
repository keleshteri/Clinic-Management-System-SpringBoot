package keleshteri.clinic.management.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

}
