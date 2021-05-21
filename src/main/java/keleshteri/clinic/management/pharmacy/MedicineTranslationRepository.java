package keleshteri.clinic.management.pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineTranslationRepository extends JpaRepository<MedicineTranslation,Long> {
}
