package keleshteri.clinic.management.pharmacy.repository;

import keleshteri.clinic.management.pharmacy.model.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineTypeRepository extends JpaRepository<MedicineType,Long> {
}
