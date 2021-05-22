package keleshteri.clinic.management.pharmacy.service;

import keleshteri.clinic.management.exception.ResourceNotFoundException;
import keleshteri.clinic.management.global.GlobalService;
import keleshteri.clinic.management.pharmacy.model.Company;
import keleshteri.clinic.management.pharmacy.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService implements GlobalService<Company> {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> all() {
        return companyRepository.findAll();
    }

    @Override
    public ResponseEntity<Company> find(Long id) {
        Company company= companyRepository.findById(id)
                         .orElseThrow(()-> new ResourceNotFoundException("Company not exist with id :" + id));
        return ResponseEntity.ok(company);
    }

    @Override
    public ResponseEntity<List<Company>> pagination() {
        List<Company> companies = companyRepository.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Range", "companies 0-"+companies.size() + "/" + companies.size());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(companies);
    }

    @Override
    public ResponseEntity<Company> create(Company company) {
        return ResponseEntity.ok(companyRepository.save(company));
    }

    @Override
    public ResponseEntity<Company> update(Long id, Company companyDetails) {
        //get the record
        Company company= companyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Company not exist with id :" + id));
        //set for update
        company.setName(companyDetails.getName());

        //patient save  data or update
        Company companyUpdate = companyRepository.save(company);

        return ResponseEntity.ok(companyUpdate);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        //get the record
        Company company= companyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Company not exist with id :" + id));

        //delete
        companyRepository.delete(company);

        //response
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
