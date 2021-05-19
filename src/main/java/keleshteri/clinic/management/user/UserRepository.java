package keleshteri.clinic.management.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

//    @Transactional
//    @Modifying
//    @Query("UPDATE User  a" +
//           "SET a.active = TRUE WHERE a.email = ?1")
//    int activeUser(String email);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE User  a" +
//            "SET a.IS_EMAIL_VERIFIED = TRUE WHERE a.email = ?1")
//    int VerifiedUserEmail(String email);

}
