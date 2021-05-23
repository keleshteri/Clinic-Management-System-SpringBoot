package keleshteri.clinic.management.global.units_measurement;

import keleshteri.clinic.management.exception.ResourceNotFoundException;
import keleshteri.clinic.management.global.GlobalService;
import keleshteri.clinic.management.global.quantity.Quantity;
import keleshteri.clinic.management.global.quantity.QuantityRepository;
import keleshteri.clinic.management.global.quantity.QuantityService;
import keleshteri.clinic.management.pharmacy.model.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UnitsMeasurementService implements GlobalService<UnitsMeasurement> {

    private  final UnitsMeasurementRepository unitsMeasurementRepository;

    @Autowired
    private QuantityService quantityService;

    @Autowired
    public UnitsMeasurementService(UnitsMeasurementRepository unitsMeasurementRepository) {
        this.unitsMeasurementRepository = unitsMeasurementRepository;
    }

    public UnitsMeasurement findModel(Long id){
        UnitsMeasurement unitsMeasurement = unitsMeasurementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Units Measurement not exist with id :" + id));
        return  unitsMeasurement;
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
            Quantity Mass    = quantityService.findName("Mass");
            Quantity Volume  = quantityService.findName("Volume");
            Quantity Amount  = quantityService.findName("Amount");
            //Mass
            UnitsMeasurement kilogram = new UnitsMeasurement(Mass,"kilogram","Kg","kilogram");
            UnitsMeasurement gram = new UnitsMeasurement(Mass,"gram","g","gram");
            UnitsMeasurement milligram = new UnitsMeasurement(Mass,"milligram","mg","milligram");
            UnitsMeasurement microgram = new UnitsMeasurement(Mass,"microgram","mcg","microgram");
            //Volume
            UnitsMeasurement litre = new UnitsMeasurement(Volume,"litre","L","litre");
            UnitsMeasurement millilitre = new UnitsMeasurement(Volume,"millilitre","ml","millilitre");
            UnitsMeasurement cubic_centimetre = new UnitsMeasurement(Volume,"cubic centimetre","cc","cubic centimetre");
            //Amount
            UnitsMeasurement mole = new UnitsMeasurement(Amount,"mole","mol","mole");
            UnitsMeasurement millimole = new UnitsMeasurement(Amount,"millimole","mmol","millimole");

            //save
            unitsMeasurementRepository.save(kilogram);
            unitsMeasurementRepository.save(gram);
            unitsMeasurementRepository.save(milligram);
            unitsMeasurementRepository.save(microgram);
            unitsMeasurementRepository.save(litre);
            unitsMeasurementRepository.save(millilitre);
            unitsMeasurementRepository.save(cubic_centimetre);
            unitsMeasurementRepository.save(mole);
            unitsMeasurementRepository.save(millimole);
        }
    }
}
