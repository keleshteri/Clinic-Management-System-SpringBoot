package keleshteri.clinic.management.global.units_measurement;

import keleshteri.clinic.management.exception.ResourceNotFoundException;
import keleshteri.clinic.management.global.quantity.Quantity;
import keleshteri.clinic.management.global.quantity.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/units/measurements")
public class UnitsMeasurementController {

    @Autowired
    private UnitsMeasurementService unitsMeasurementService;

    @Autowired
    private QuantityService quantityService;

    @GetMapping()
    public ResponseEntity<List<UnitsMeasurement>> getAll() {
        return unitsMeasurementService.pagination();
    }

    @PostMapping
    public ResponseEntity<UnitsMeasurement> create(@Valid @RequestBody UnitsMeasurementRequest unitsRequest) {
        //get quantity
        Quantity quantity = quantityService.findModel(unitsRequest.getQuantity());
        //set
        UnitsMeasurement unitsMeasurement =  new UnitsMeasurement();
        unitsMeasurement.setQuantity(quantity);
        unitsMeasurement.setName(unitsRequest.getName());
        unitsMeasurement.setDescription(unitsRequest.getDescription());

        return unitsMeasurementService.create(unitsMeasurement);
    }

    // get  by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<UnitsMeasurement> getById(@PathVariable Long id) {

        return unitsMeasurementService.find(id);
    }

    //update  rest api
    @PutMapping("/{id}")
    public ResponseEntity<UnitsMeasurement> update(@PathVariable Long id, @RequestBody  UnitsMeasurementRequest unitsRequest) {
        //get quantity
        Quantity quantity = quantityService.findModel(unitsRequest.getQuantity());
        //set
        UnitsMeasurement unitsMeasurement =  new UnitsMeasurement();
        unitsMeasurement.setQuantity(quantity);
        unitsMeasurement.setName(unitsRequest.getName());
        unitsMeasurement.setDescription(unitsRequest.getDescription());

        return unitsMeasurementService.update(id,unitsMeasurement);
    }

    //delete  rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        return unitsMeasurementService.delete(id);
    }

}
