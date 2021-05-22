package keleshteri.clinic.management.pharmacy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@Table(name = "medicine_types")
public class MedicineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please Enter medicine type's Code")
    @Column(unique = true,nullable = false)
    private String code;

    @NotNull(message = "Please Enter medicine type's Name")
    @Column(unique = true,nullable = false)
    private String name;
}
