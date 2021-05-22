package keleshteri.clinic.management.global.units_measurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsMeasurementRepository extends JpaRepository<UnitsMeasurement,Long> {
}
