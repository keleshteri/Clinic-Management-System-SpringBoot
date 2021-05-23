package keleshteri.clinic.management.patient.model;

import keleshteri.clinic.management.attribute.AttributeStorageType;
import keleshteri.clinic.management.attribute.AttributeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "patient_attribute")
public class PatientAttribute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please enter  Code")
    @Column(unique = true,nullable = false)
    private String code;


    private AttributeType attributeType;

    private AttributeStorageType attributeStorageType;

//    @Column(columnDefinition = "TEXT")
    @Lob
    @Column( length = 100000 )
    private String configuration;

    @Column
    private Integer position;

    @Column
    private Boolean translatable;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}
