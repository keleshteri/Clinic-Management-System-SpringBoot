package keleshteri.clinic.management.global.quantity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityController {

    @Autowired
    private QuantityService quantityService;

    @GetMapping()
    public ResponseEntity<List<Quantity>> getAll() {
        return quantityService.pagination();
    }

    @PostMapping
    public ResponseEntity<Quantity> create(@Valid @RequestBody Quantity quantity) {
        return quantityService.create(quantity);
    }

    // get  by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Quantity> getById(@PathVariable Long id) {
        return quantityService.find(id);
    }

    //update  rest api
    @PutMapping("/{id}")
    public ResponseEntity<Quantity> update(@PathVariable Long id, @RequestBody  Quantity quantityDetails) {
        return quantityService.update(id,quantityDetails);
    }

    //delete  rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        return quantityService.delete(id);
    }

}
