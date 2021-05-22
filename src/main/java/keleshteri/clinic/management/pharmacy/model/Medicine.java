package keleshteri.clinic.management.pharmacy.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "medicines")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(nullable = false,unique = true)
    private Integer code;

    private String name;
    private String description;

    @OneToMany(mappedBy = "medicine",fetch = FetchType.LAZY)
    private Set<MedicineTranslation> medicineTranslations;




}