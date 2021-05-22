package keleshteri.clinic.management.pharmacy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@Table(name = "medicine_companies")
public class MedicineCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please enter Company Name")
    @Column(unique = true,nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

}
