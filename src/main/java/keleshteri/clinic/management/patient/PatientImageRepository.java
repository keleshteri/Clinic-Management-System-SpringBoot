package keleshteri.clinic.management.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientImageRepository  extends JpaRepository<PatientImage,Long> {

    Optional<PatientImage> findByName(String name);
    Optional<Patient> findByIdAndPatientId(Long id, Long patientId);
}
