package keleshteri.clinic.management.pharmacy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "medicines")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(nullable = false,unique = true)
    private Integer code;

    @NotNull(message = "Please enter  Name")
    @Column(unique = true,nullable = false)
    private String name;


    private String description;

    @OneToMany(mappedBy = "medicine",fetch = FetchType.LAZY)
    private Set<MedicineTranslation> medicineTranslations;


    public Medicine(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
