package keleshteri.clinic.management.patient;

import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface PatientService {

    public List<Patient> all();

    public ResponseEntity<Patient> find(Long id);

    public ResponseEntity<List<Patient>> pagination();

    public ResponseEntity<Patient> create(Patient patient);

    public ResponseEntity<Patient> update(Long id,Patient patient);

    public ResponseEntity<Map<String, Boolean>> delete(Long id);

}
