package keleshteri.clinic.management.patient;

import keleshteri.clinic.management.exception.RecordExistsException;
import keleshteri.clinic.management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> all() {
        return patientRepository.findAll();
    }

    @Override
    public ResponseEntity<Patient> find(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
        return ResponseEntity.ok(patient);
    }

    @Override
    public ResponseEntity<List<Patient>> pagination() {
        //get all list
        List<Patient> patient= patientRepository.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Range", "patients 0-"+patient.size() + "/" + patient.size());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(patient);
    }

    @Override
    public ResponseEntity<Patient> create(Patient patient) {
        //check if findByFileNumber
        boolean existsFileNumber = patientRepository.existsByFileNumber(patient.getFileNumber());
        if(existsFileNumber){
            throw  new RecordExistsException("Patient exist with FileNumber: "+ patient.getFileNumber());
        }
        //exists nationalId
        boolean existsNationalId = patientRepository.existsByNationalId(patient.getNationalId());
        if(existsNationalId){
            throw  new RecordExistsException("Patient exist with NationalId: "+ patient.getNationalId());
        }


//                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" ));
//        try{
        return ResponseEntity.ok(patientRepository.save(patient));
//            return new ResponseEntity<Patient>(HttpStatus.FORBIDDEN);
//        }
//        catch (DataIntegrityViolationException e){
//            System.out.println("history already exist");
//            System.out.println(e.getMessage());
//            System.out.println(e);
////            throw new IllegalStateException("email already confirmed");
//        }
//        finally {
//            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND) ;
//        }
//        catch (javax.validation.ConstraintViolationException cve){
//
//            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND) ;
//        }

    }

    @Override
    public ResponseEntity<Patient> update(Long id, Patient patientDetails) {
        System.out.println("update-Patient");
        //check if patient id
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
        //set for update
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setMiddleName(patientDetails.getMiddleName());
        patient.setNationalId(patientDetails.getNationalId());
        patient.setFileNumber(patientDetails.getFileNumber());
        patient.setGender(patientDetails.getGender());
        patient.setBirthDate(patientDetails.getBirthDate());
        patient.setEmail(patientDetails.getEmail());
        patient.setCellphoneNumber(patientDetails.getCellphoneNumber());
        patient.setLandlineNumberHome(patientDetails.getLandlineNumberHome());
        patient.setLandlineNumberOffice(patientDetails.getLandlineNumberOffice());
        patient.setHomeAddress(patientDetails.getHomeAddress());
        //patient save  data or update
        Patient updatedPatient = patientRepository.save(patient);

        return ResponseEntity.ok(updatedPatient);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));

        patientRepository.delete(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
