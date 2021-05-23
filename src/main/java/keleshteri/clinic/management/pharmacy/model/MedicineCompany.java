package keleshteri.clinic.management.pharmacy.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "medicine_companies")
public class MedicineCompany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please enter Company Name")
    @Column(unique = true,nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    //Constructor


    public MedicineCompany(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
