package keleshteri.clinic.management.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import keleshteri.clinic.management.locale.Locale;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(
        name = "medicine_type_translation",
        uniqueConstraints=
        @UniqueConstraint(columnNames = {"medicine_type_id", "locale_id"})
)
public class MedicineTypeTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    //name
    @NotNull(message = "Please enter  Name")
    @Column(unique = true,nullable = false)
    private String name;

    //medicineType
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_type_id", nullable = false )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MedicineType medicineType;

    //locale
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "locale_id", nullable = false )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Locale locale;

}
