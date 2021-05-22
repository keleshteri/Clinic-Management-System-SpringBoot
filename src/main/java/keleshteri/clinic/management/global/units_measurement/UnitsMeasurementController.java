package keleshteri.clinic.management.global.units_measurement;

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

    @GetMapping()
    public ResponseEntity<List<UnitsMeasurement>> getAll() {
        return unitsMeasurementService.pagination();
    }

    @PostMapping
    public ResponseEntity<UnitsMeasurement> create(@Valid @RequestBody UnitsMeasurement unitsMeasurement) {
        return unitsMeasurementService.create(unitsMeasurement);
    }

    // get  by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<UnitsMeasurement> getById(@PathVariable Long id) {
        return unitsMeasurementService.find(id);
    }

    //update  rest api
    @PutMapping("/{id}")
    public ResponseEntity<UnitsMeasurement> update(@PathVariable Long id, @RequestBody  UnitsMeasurement unitsMeasurementDetails) {
        return unitsMeasurementService.update(id,unitsMeasurementDetails);
    }

    //delete  rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        return unitsMeasurementService.delete(id);
    }

}
