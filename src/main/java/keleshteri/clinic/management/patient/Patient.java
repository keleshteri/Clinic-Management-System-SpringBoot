package keleshteri.clinic.management.patient;


import keleshteri.clinic.management.person.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@Validated
@Entity
@Table(name = "patients")
public class Patient extends Person implements Serializable {



    @NotNull(message = "Please enter fileNumber")
    @Column(unique = true,nullable = false)
    private Long fileNumber;


//    @CreatedBy
//    protected U createdBy;

//    @CreatedDate
//    @Temporal(TIMESTAMP)
//    protected Date createdDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}
