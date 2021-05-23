package keleshteri.clinic.management.patient.controller;

import keleshteri.clinic.management.patient.model.Patient;
import keleshteri.clinic.management.patient.service.PatientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping()
    ResponseEntity<List<Patient>> getAll()
    {
        return patientService.pagination();
    }

    // create Patient rest api
    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        return patientService.create(patient);
    }

    // get Patient by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.find(id);
    }

    //update Patient rest api
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails){

        return patientService.update(id,patientDetails);
    }

    //delete Patient rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long id){
        return   patientService.delete(id);
    }
}
