package keleshteri.clinic.management.pharmacy.controller;

import keleshteri.clinic.management.pharmacy.service.CompanyService;
import keleshteri.clinic.management.pharmacy.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController   {

    @Autowired
    private CompanyService companyService;

    @GetMapping()
    public ResponseEntity<List<Company>> getAll() {
        return companyService.pagination();
    }

    @PostMapping
    public ResponseEntity<Company> create(@Valid @RequestBody Company company) {
        return companyService.create(company);
    }

    // get Patient by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Long id) {
        return companyService.find(id);
    }

    //update Patient rest api
    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @RequestBody Company companyDetails) {
        return companyService.update(id,companyDetails);
    }

    //delete Patient rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        return companyService.delete(id);
    }
}
