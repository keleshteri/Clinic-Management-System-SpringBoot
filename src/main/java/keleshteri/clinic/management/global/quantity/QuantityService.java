package keleshteri.clinic.management.global.quantity;

import keleshteri.clinic.management.exception.ResourceNotFoundException;
import keleshteri.clinic.management.global.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuantityService implements GlobalService<Quantity> {

    private final QuantityRepository quantityRepository;

    @Autowired
    public QuantityService(QuantityRepository quantityRepository) {
        this.quantityRepository = quantityRepository;
    }

    @Override
    public List<Quantity> all() {
        return quantityRepository.findAll();
    }

    public  Quantity findModel(Long id){
          Quantity quantity = quantityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quantity not exist with id :" + id));
          return  quantity;
    }

    @Override
    public ResponseEntity<Quantity> find(Long id) {
        Quantity quantity = quantityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quantity not exist with id :" + id));
        return ResponseEntity.ok(quantity);
    }

    @Override
    public ResponseEntity<List<Quantity>> pagination() {
        List<Quantity> quantities= quantityRepository.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Range", "quantities 0-"+quantities.size() + "/" + quantities.size());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(quantities);
    }

    @Override
    public ResponseEntity<Quantity> create(Quantity quantity) {
        return ResponseEntity.ok(quantityRepository.save(quantity));
    }

    @Override
    public ResponseEntity<Quantity> update(Long id, Quantity quantityDetails) {
        Quantity quantity = quantityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quantity not exist with id :" + id));
        quantity.setName(quantityDetails.getName());
        quantity.setDescription(quantityDetails.getDescription());

        return ResponseEntity.ok(quantityRepository.save(quantity));
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        Quantity quantity = quantityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quantity not exist with id :" + id));
        //delete
        quantityRepository.delete(quantity);
        //response
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public void seeder(){
        if(quantityRepository.count()==0){
            Quantity mass   = new Quantity("Mass","Mass (weight)");
            Quantity volume = new Quantity("Volume","Volume");
            Quantity amount = new Quantity("Amount","Amount");

            quantityRepository.save(mass);
            quantityRepository.save(volume);
            quantityRepository.save(amount);

        }
    }
}
