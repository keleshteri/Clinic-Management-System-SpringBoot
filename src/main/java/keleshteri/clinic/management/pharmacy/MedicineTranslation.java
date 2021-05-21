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
        @UniqueConstraint(columnNames = {"medicine_id", "locale"})
)
public class MedicineTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_id", nullable = false )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
//    @JoinColumn(name = "locale",table = "medicine_translation",referencedColumnName = "locale",foreignKey = @ForeignKey(name = "code",value = ConstraintMode.CONSTRAINT))
//    @JoinColumn(columnDefinition = "locale_id",foreignKey = @ForeignKey(name = "code",value = ConstraintMode.NO_CONSTRAINT))
//    @JoinTable(name="locales", joinColumns=@JoinColumn(referencedColumnName = "locale",foreignKey = @ForeignKey(name = "code")))
    @JoinColumn(name = "locale", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Locale locale;



}
