package keleshteri.clinic.management.pharmacy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import keleshteri.clinic.management.locale.Locale;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(
        name = "medicine_translation",
        uniqueConstraints=
        @UniqueConstraint(columnNames = {"medicine_id", "locale_id"})
)
public class MedicineTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medicine_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "locale_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Locale locale;

    private String name;
    private String description;
}
