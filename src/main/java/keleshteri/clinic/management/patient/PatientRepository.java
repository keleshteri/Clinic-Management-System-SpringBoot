package keleshteri.clinic.management.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

//    Page<Patient> findByFileNumberPage(Long fileNumber, Pageable pageable);
    Optional<Patient> findByFileNumber(Long fileNumber);
    boolean existsByFileNumber(Long fileNumber);

    boolean existsByNationalId(String nationalId);

    Page<Patient> findByLastNameContaining(String lastName, Pageable pageable);
}
