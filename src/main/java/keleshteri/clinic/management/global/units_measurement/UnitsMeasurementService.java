package keleshteri.clinic.management.global.units_measurement;

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
public class UnitsMeasurementService implements GlobalService<UnitsMeasurement> {

    private  final UnitsMeasurementRepository unitsMeasurementRepository;

    @Autowired
    public UnitsMeasurementService(UnitsMeasurementRepository unitsMeasurementRepository) {
        this.unitsMeasurementRepository = unitsMeasurementRepository;
    }

    @Override
    public List<UnitsMeasurement> all() {
        return unitsMeasurementRepository.findAll();
    }

    @Override
    public ResponseEntity<UnitsMeasurement> find(Long id) {
        UnitsMeasurement unitsMeasurement = unitsMeasurementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Units Measurement not exist with id :" + id));
        return ResponseEntity.ok(unitsMeasurement);
    }

    @Override
    public ResponseEntity<List<UnitsMeasurement>> pagination() {
        List<UnitsMeasurement> unitsMeasurements = unitsMeasurementRepository.findAll();

        System.out.println("here to pagination ");
        unitsMeasurements.forEach(u -> {
            System.out.println(u.getName());
            System.out.println(u.getQuantity());
        });
        System.out.println(unitsMeasurements);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Range", "unitsMeasurements 0-"+unitsMeasurements.size() + "/" + unitsMeasurements.size());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(unitsMeasurements);

    }

    @Override
    public ResponseEntity<UnitsMeasurement> create(UnitsMeasurement unitsMeasurement) {
        System.out.println("here save UnitsMeasurement");
        System.out.println(unitsMeasurement.getName());
        System.out.println(unitsMeasurement.getQuantity());
        return ResponseEntity.ok(unitsMeasurementRepository.save(unitsMeasurement));
    }

    @Override
    public ResponseEntity<UnitsMeasurement> update(Long id, UnitsMeasurement unitsMeasurementDetails) {
        UnitsMeasurement unitsMeasurement = unitsMeasurementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Units Measurement not exist with id :" + id));

        //set
        unitsMeasurement.setName(unitsMeasurementDetails.getName());
        unitsMeasurement.setDescription(unitsMeasurement.getDescription());

        return ResponseEntity.ok(unitsMeasurementRepository.save(unitsMeasurement));
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        UnitsMeasurement unitsMeasurement = unitsMeasurementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Units Measurement not exist with id :" + id));

        //delete
        unitsMeasurementRepository.delete(unitsMeasurement);
        //response
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public void seeder(){
        if(unitsMeasurementRepository.count()==0){

        }
    }
}
