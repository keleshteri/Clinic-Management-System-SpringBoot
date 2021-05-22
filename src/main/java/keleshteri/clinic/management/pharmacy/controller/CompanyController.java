package keleshteri.clinic.management.pharmacy.controller;

import keleshteri.clinic.management.pharmacy.service.CompanyService;
import keleshteri.clinic.management.pharmacy.model.MedicineCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/medicine/companies")
public class CompanyController   {

    @Autowired
    private CompanyService companyService;

    @GetMapping()
    public ResponseEntity<List<MedicineCompany>> getAll() {
        return companyService.pagination();
    }

    @PostMapping
    public ResponseEntity<MedicineCompany> create(@Valid @RequestBody MedicineCompany medicineCompany) {
        return companyService.create(medicineCompany);
    }

    // get Patient by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<MedicineCompany> getById(@PathVariable Long id) {
        return companyService.find(id);
    }

    //update Patient rest api
    @PutMapping("/{id}")
    public ResponseEntity<MedicineCompany> update(@PathVariable Long id, @RequestBody MedicineCompany medicineCompanyDetails) {
        return companyService.update(id, medicineCompanyDetails);
    }

    //delete Patient rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        return companyService.delete(id);
    }
}
