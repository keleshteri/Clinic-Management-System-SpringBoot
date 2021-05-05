package keleshteri.clinic.management.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Page<Patient> findByFileNumber(Long fileNumber, Pageable pageable);
    Page<Patient> findByLastNameContaining(String lastName, Pageable pageable);
}
