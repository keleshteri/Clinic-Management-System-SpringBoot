package keleshteri.clinic.management.global.quantity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity,Long> {


    Optional<Quantity> findByName(String name);
}
