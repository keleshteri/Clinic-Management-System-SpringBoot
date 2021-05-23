package keleshteri.clinic.management.pharmacy.service;

import keleshteri.clinic.management.exception.ResourceNotFoundException;
import keleshteri.clinic.management.global.GlobalService;
import keleshteri.clinic.management.pharmacy.model.MedicineProduct;
import keleshteri.clinic.management.pharmacy.repository.MedicineProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicineProductService implements GlobalService<MedicineProduct> {

    private final MedicineProductRepository medicineProductRepository;

    @Autowired
    public MedicineProductService(MedicineProductRepository medicineProductRepository) {
        this.medicineProductRepository = medicineProductRepository;
    }

    @Override
    public List<MedicineProduct> all() {
        return medicineProductRepository.findAll();
    }

    @Override
    public ResponseEntity<MedicineProduct> find(Long id) {
        MedicineProduct medicineProduct = medicineProductRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("medicineProduct not exist with id :" + id));
        return ResponseEntity.ok(medicineProduct);
    }

    @Override
    public ResponseEntity<List<MedicineProduct>> pagination() {
        List<MedicineProduct> medicineProducts =medicineProductRepository.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Range", "medicineProducts 0-"+medicineProducts.size() + "/" + medicineProducts.size());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(medicineProducts);
    }

    @Override
    public ResponseEntity<MedicineProduct> create(MedicineProduct medicineProduct) {
        return ResponseEntity.ok(medicineProductRepository.save(medicineProduct));
    }

    @Override
    public ResponseEntity<MedicineProduct> update(Long id, MedicineProduct medicineProductDetails) {
        MedicineProduct medicineProduct = medicineProductRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("medicineProduct not exist with id :" + id));
        //set for update
        medicineProduct.setCode(medicineProductDetails.getCode());
        medicineProduct.setName(medicineProductDetails.getName());
        medicineProduct.setMedicine(medicineProductDetails.getMedicine());
        medicineProduct.setMedicineCompany(medicineProductDetails.getMedicineCompany());
        medicineProduct.setDoseUnits(medicineProductDetails.getDoseUnits());
        medicineProduct.setDose(medicineProductDetails.getDose());
        medicineProduct.setMedicineType(medicineProductDetails.getMedicineType());

        //save
        return ResponseEntity.ok(medicineProductRepository.save(medicineProduct));
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        MedicineProduct medicineProduct = medicineProductRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("medicineProduct not exist with id :" + id));

        //delete
        medicineProductRepository.delete(medicineProduct);

        //response
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
