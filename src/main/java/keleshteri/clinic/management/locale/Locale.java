package keleshteri.clinic.management.locale;

import keleshteri.clinic.management.pharmacy.MedicineTranslation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "locales")
public class Locale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;


    @NotNull(message = "Please Enter code")
    @Column(unique = true,length = 12,updatable = false,nullable = false)
    private String code;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "locale", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<MedicineTranslation> medicineTranslations;

    ///Constructor


    public Locale() {
    }

    public Locale(String code) {
        this.code = code;
    }
}
