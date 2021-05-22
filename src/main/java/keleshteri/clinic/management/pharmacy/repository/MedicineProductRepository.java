package keleshteri.clinic.management.pharmacy.repository;

import keleshteri.clinic.management.pharmacy.model.MedicineProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineProductRepository extends JpaRepository<MedicineProduct,Long> {
}
