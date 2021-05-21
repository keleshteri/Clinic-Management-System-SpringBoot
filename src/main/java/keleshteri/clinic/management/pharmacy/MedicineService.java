package keleshteri.clinic.management.pharmacy;

import keleshteri.clinic.management.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    ResponseEntity<List<Medicine>> pagination(){
        List<Medicine> medicines= medicineRepository.findAll();
        System.out.println(medicines.size());
        //responseHeaders
        HttpHeaders responseHeaders = new HttpHeaders();
        //set Content-Range
        responseHeaders.set("Content-Range", "medicines 0-"+medicines.size() + "/" + medicines.size());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(medicines);
    }

    public List<Medicine> all(){
       return   medicineRepository.findAll();
    }

    public ResponseEntity<Medicine> find(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not exist with id :" + id));
        return ResponseEntity.ok(medicine);
    }

    public ResponseEntity<Medicine> create(Medicine medicine) {
        return ResponseEntity.ok(medicineRepository.save(medicine));
    }

    public ResponseEntity<Medicine> update(Long id, Medicine medicineDetails) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not exist with id :" + id));

        //set for update
        medicine.setCode(medicineDetails.getCode());
        medicine.setName(medicineDetails.getName());
        medicine.setDescription(medicineDetails.getDescription());
        //medicine save  data or update

        Medicine updatedMedicine = medicineRepository.save(medicine);
        return ResponseEntity.ok(updatedMedicine);
    }
}
