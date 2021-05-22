package keleshteri.clinic.management.pharmacy.controller;

import keleshteri.clinic.management.pharmacy.model.Medicine;
import keleshteri.clinic.management.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    //get
    @GetMapping()
    ResponseEntity<List<Medicine>> getAll(){
        System.out.println("Medicine");
        return medicineService.pagination();
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getById(@PathVariable Long id){
        return medicineService.find(id);
    }


    //Post create
    @PostMapping
    public ResponseEntity<Medicine>create(@Valid @RequestBody Medicine medicine){
        return medicineService.create(medicine);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Medicine>update(@PathVariable Long id, @RequestBody Medicine medicineDetails){
        return medicineService.update(id,medicineDetails);
    }

    //Delete
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
//        return   medicineService.delete(id);
//    }


}
