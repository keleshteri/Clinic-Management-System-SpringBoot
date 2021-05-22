package keleshteri.clinic.management.pharmacy.controller;

import keleshteri.clinic.management.pharmacy.model.MedicineType;
import keleshteri.clinic.management.pharmacy.service.MedicineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/medicine/types")
public class MedicineTypeController {

    @Autowired
     private MedicineTypeService medicineTypeService;

    //get
    @GetMapping()
    public ResponseEntity<List<MedicineType>> getAll(){
        return medicineTypeService.pagination();
    }

    //Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<MedicineType> getById(@PathVariable Long id){
        return medicineTypeService.find(id);
    }

    //Create
    @PostMapping
    public ResponseEntity<MedicineType>create(@Valid @RequestBody MedicineType medicineType){
        return  medicineTypeService.create(medicineType);
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<MedicineType>update(@PathVariable Long id,@RequestBody MedicineType medicineType){
        return medicineTypeService.update(id,medicineType);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        return   medicineTypeService.delete(id);
    }
}
