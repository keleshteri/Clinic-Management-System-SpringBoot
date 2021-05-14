package keleshteri.clinic.management.doctor;

import keleshteri.clinic.management.person.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@Entity
@Table(name = "doctors")
public class Doctor extends Person implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "specialty_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private  Specialty specialty;

    private String position;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}
