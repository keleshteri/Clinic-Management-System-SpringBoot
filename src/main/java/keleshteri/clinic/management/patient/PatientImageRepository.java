package keleshteri.clinic.management.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientImageRepository  extends JpaRepository<PatientImage,Long> {

    Optional<PatientImage> findByName(String name);
    Optional<Patient> findByIdAndPatientId(Long id, Long patientId);
}
