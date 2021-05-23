package keleshteri.clinic.management.pharmacy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "medicine_types")
public class MedicineType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please Enter medicine type's Code")
    @Column(unique = true,nullable = false)
    private Integer code;

    @NotNull(message = "Please Enter medicine type's Name")
    @Column(unique = true,nullable = false)
    private String name;

    //Constructor


    public MedicineType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
