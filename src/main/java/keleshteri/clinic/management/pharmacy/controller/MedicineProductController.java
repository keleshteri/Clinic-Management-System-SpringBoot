package keleshteri.clinic.management.pharmacy.controller;

import keleshteri.clinic.management.global.units_measurement.UnitsMeasurement;
import keleshteri.clinic.management.global.units_measurement.UnitsMeasurementService;
import keleshteri.clinic.management.pharmacy.model.Medicine;
import keleshteri.clinic.management.pharmacy.model.MedicineCompany;
import keleshteri.clinic.management.pharmacy.model.MedicineProduct;
import keleshteri.clinic.management.pharmacy.repository.MedicineProductRepository;
import keleshteri.clinic.management.pharmacy.request.MedicineProductRequest;
import keleshteri.clinic.management.pharmacy.service.MedicineCompanyService;
import keleshteri.clinic.management.pharmacy.service.MedicineProductService;
import keleshteri.clinic.management.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/medicine/products")
public class MedicineProductController {

    @Autowired
    private MedicineProductService medicineProductService;
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineCompanyService medicineCompanyService;

    @Autowired
    private UnitsMeasurementService unitsMeasurementService;


    @GetMapping()
    public ResponseEntity<List<MedicineProduct>> getAll() {
        return medicineProductService.pagination();
    }

    @PostMapping
    public ResponseEntity<MedicineProduct> create(@Valid @RequestBody MedicineProductRequest request) {
        //get medicine
        Medicine medicine = medicineService.findModel(request.getMedicine());
        //
        MedicineCompany medicineCompany = medicineCompanyService.findModel(request.getCompany());
        //
        UnitsMeasurement unitsMeasurement = unitsMeasurementService.findModel(request.getUnits());
        //set
        MedicineProduct medicineProduct = new MedicineProduct();
        medicineProduct.setCode(request.getCode());
        medicineProduct.setName(request.getName());
        medicineProduct.setMedicine(medicine);
        medicineProduct.setMedicineCompany(medicineCompany);
        medicineProduct.setDoseUnits(unitsMeasurement);
        medicineProduct.setDose(request.getDose());

        return medicineProductService.create(medicineProduct);
    }

    // get  by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<MedicineProduct> getById(@PathVariable Long id) {

        return medicineProductService.find(id);
    }

    //update  rest api
    @PutMapping("/{id}")
    public ResponseEntity<MedicineProduct> update(@PathVariable Long id, @RequestBody  MedicineProductRequest request) {
        //get medicine
        Medicine medicine = medicineService.findModel(request.getMedicine());
        //
        MedicineCompany medicineCompany = medicineCompanyService.findModel(request.getCompany());
        //
        UnitsMeasurement unitsMeasurement = unitsMeasurementService.findModel(request.getUnits());
        //set
        MedicineProduct medicineProduct = new MedicineProduct();
        medicineProduct.setCode(request.getCode());
        medicineProduct.setName(request.getName());
        medicineProduct.setMedicine(medicine);
        medicineProduct.setMedicineCompany(medicineCompany);
        medicineProduct.setDoseUnits(unitsMeasurement);
        medicineProduct.setDose(request.getDose());

        return medicineProductService.update(id,medicineProduct);
    }

    //delete  rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        return medicineProductService.delete(id);
    }


}
