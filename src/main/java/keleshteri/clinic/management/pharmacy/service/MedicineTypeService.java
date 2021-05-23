package keleshteri.clinic.management.pharmacy.service;

import keleshteri.clinic.management.exception.ResourceNotFoundException;
import keleshteri.clinic.management.global.GlobalService;
import keleshteri.clinic.management.pharmacy.model.MedicineType;
import keleshteri.clinic.management.pharmacy.repository.MedicineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicineTypeService implements GlobalService<MedicineType> {

     private final MedicineTypeRepository medicineTypeRepository;

     @Autowired
    public MedicineTypeService(MedicineTypeRepository medicineTypeRepository) {
        this.medicineTypeRepository = medicineTypeRepository;
    }


    public MedicineType findModel(Long id){
        MedicineType medicineType = medicineTypeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("medicineType not exist with id :" + id));
        return medicineType;
    }

    @Override
    public List<MedicineType> all() {
        return medicineTypeRepository.findAll();
    }

    @Override
    public ResponseEntity<MedicineType> find(Long id) {
         MedicineType medicineType = medicineTypeRepository.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("medicineType not exist with id :" + id));

        return ResponseEntity.ok(medicineType);
    }

    @Override
    public ResponseEntity<List<MedicineType>> pagination() {
         //list
        List<MedicineType> medicineTypeList = medicineTypeRepository.findAll();

        //Header
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Range","medicine_types 0-"+medicineTypeList.size() + "/" + medicineTypeList.size());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(medicineTypeList);
    }

    @Override
    public ResponseEntity<MedicineType> create(MedicineType medicineType) {
        return ResponseEntity.ok(medicineTypeRepository.save(medicineType));
    }

    @Override
    public ResponseEntity<MedicineType> update(Long id, MedicineType medicineTypeDetails) {
        MedicineType medicineType = medicineTypeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("medicineType not exist with id :" + id));

        medicineType.setName(medicineTypeDetails.getName());

        MedicineType medicineTypeUpdate =medicineTypeRepository.save(medicineType);

        return ResponseEntity.ok(medicineTypeUpdate);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        MedicineType medicineType = medicineTypeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("medicineType not exist with id :" + id));

        //
        medicineTypeRepository.delete(medicineType);

        //response
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
