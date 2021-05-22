package keleshteri.clinic.management.pharmacy.repository;

import keleshteri.clinic.management.pharmacy.model.MedicineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<MedicineCompany,Long> {
}
