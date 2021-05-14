package keleshteri.clinic.management.pharmacy;

import javax.persistence.*;

@Entity
@Table(name = "medicine_translation")
public class MedicineTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    private String name;
    private String description;
}
