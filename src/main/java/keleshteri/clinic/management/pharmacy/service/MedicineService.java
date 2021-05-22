package keleshteri.clinic.management.pharmacy.service;

import keleshteri.clinic.management.exception.ResourceNotFoundException;

import keleshteri.clinic.management.pharmacy.model.Medicine;
import keleshteri.clinic.management.pharmacy.model.MedicineTranslation;
import keleshteri.clinic.management.pharmacy.repository.MedicineRepository;
import keleshteri.clinic.management.pharmacy.repository.MedicineTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    private final MedicineTranslationRepository medicineTranslationRepository;
    MedicineTranslation medicineTranslation;
    @Autowired
    public MedicineService(MedicineRepository medicineRepository, MedicineTranslationRepository medicineTranslationRepository) {
        this.medicineRepository = medicineRepository;

        this.medicineTranslationRepository = medicineTranslationRepository;
    }

    public ResponseEntity<List<Medicine>> pagination(){
        List<Medicine> medicines= medicineRepository.findAll();
        System.out.println(medicines.size());
        medicineTranslationRepository.findAll().forEach(medicineTranslation1 -> {
            System.out.println(medicineTranslation1.getLocale().getCode());
        });
        System.out.println(medicineTranslationRepository.findAll());

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
