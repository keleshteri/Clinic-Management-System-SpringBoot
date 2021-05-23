package keleshteri.clinic.management.pharmacy.service;

import keleshteri.clinic.management.exception.ResourceNotFoundException;
import keleshteri.clinic.management.global.GlobalService;
import keleshteri.clinic.management.pharmacy.model.Medicine;
import keleshteri.clinic.management.pharmacy.model.MedicineCompany;
import keleshteri.clinic.management.pharmacy.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicineCompanyService implements GlobalService<MedicineCompany> {

    private final CompanyRepository companyRepository;

    @Autowired
    public MedicineCompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public MedicineCompany findModel(Long id){
        MedicineCompany medicineCompany = companyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Company not exist with id :" + id));
        return  medicineCompany;
    }

    @Override
    public List<MedicineCompany> all() {
        return companyRepository.findAll();
    }

    @Override
    public ResponseEntity<MedicineCompany> find(Long id) {
        MedicineCompany medicineCompany = companyRepository.findById(id)
                         .orElseThrow(()-> new ResourceNotFoundException("Company not exist with id :" + id));
        return ResponseEntity.ok(medicineCompany);
    }

    @Override
    public ResponseEntity<List<MedicineCompany>> pagination() {
        List<MedicineCompany> companies = companyRepository.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Range", "companies 0-"+companies.size() + "/" + companies.size());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(companies);
    }

    @Override
    public ResponseEntity<MedicineCompany> create(MedicineCompany medicineCompany) {
        return ResponseEntity.ok(companyRepository.save(medicineCompany));
    }

    @Override
    public ResponseEntity<MedicineCompany> update(Long id, MedicineCompany medicineCompanyDetails) {
        //get the record
        MedicineCompany medicineCompany = companyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Company not exist with id :" + id));
        //set for update
        medicineCompany.setName(medicineCompanyDetails.getName());
        medicineCompany.setDescription(medicineCompanyDetails.getDescription());

        //patient save  data or update
        MedicineCompany medicineCompanyUpdate = companyRepository.save(medicineCompany);

        return ResponseEntity.ok(medicineCompanyUpdate);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        //get the record
        MedicineCompany medicineCompany = companyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Company not exist with id :" + id));

        //delete
        companyRepository.delete(medicineCompany);

        //response
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public void seeder(){
        if(companyRepository.count()==0){
            MedicineCompany DarouPakhsh = new MedicineCompany("Darou Pakhsh","Darou Pakhsh Pharmaceutical Mfg. Co");
            MedicineCompany Zagrosdarou = new MedicineCompany("Zagros Darou","Zagros Darou");
            MedicineCompany DarouDarmanPars = new MedicineCompany("Darou Darman Pars","Darou Darman Pars");
            MedicineCompany MehrDarou= new MedicineCompany("Mehr Darou","Mehr Darou");
            MedicineCompany Exir = new MedicineCompany("Exir","Exir Pharmaceutical Co");
            MedicineCompany Faran= new MedicineCompany("Faran","Faran");
            MedicineCompany FaranShimi= new MedicineCompany("Faran Shimi","Faran Shimi");
            MedicineCompany Temad= new MedicineCompany("Temad","Temad");

            //save
            companyRepository.save(DarouPakhsh);
            companyRepository.save(Zagrosdarou);
            companyRepository.save(DarouDarmanPars);
            companyRepository.save(MehrDarou);
            companyRepository.save(Exir);
            companyRepository.save(Faran);
            companyRepository.save(FaranShimi);
            companyRepository.save(Temad);
        }
    }


}
