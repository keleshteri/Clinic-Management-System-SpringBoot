package keleshteri.clinic.management.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import keleshteri.clinic.management.pharmacy.model.Medicine;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

//@Getter
//@Setter
//@Entity
//@Table(name = "prescription_medicines")
public class PrescriptionMedicine implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Prescription
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "prescription_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private  Prescription prescription;

    //Medicine
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "medicine_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Medicine medicine;

    @Column(name = "dailyDose",nullable = false)
    private Integer  DailyDose;



}
