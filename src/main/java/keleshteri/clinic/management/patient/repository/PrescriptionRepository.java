package keleshteri.clinic.management.patient.repository;

import keleshteri.clinic.management.patient.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
}
