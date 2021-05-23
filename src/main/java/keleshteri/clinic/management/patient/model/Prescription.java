package keleshteri.clinic.management.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import keleshteri.clinic.management.global.units_measurement.UnitsMeasurement;
import keleshteri.clinic.management.pharmacy.model.Medicine;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "prescriptions")
public class Prescription implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Patient
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "patient_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Patient patient;

    @Temporal(TemporalType.DATE)
    @Column(name = "toDayDate")
    private Date toDayDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private  Date expiryDate;



    //Medicines
//    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
//    private Set<PrescriptionMedicine> prescriptionMedicineSet = new HashSet<>();

    //Medicine
//    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name = "medicine_id",nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
////    @JsonIgnore
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private Medicine medicine;

//    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name = "dose_units_id",nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
////    @JsonIgnore
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private UnitsMeasurement doseUnits;
//
//    @Column(name = "dailyDose",nullable = false)
//    private Double dailyDose;
//
//    @Column(name = "treatmentPeriodDay",nullable = false)
//    private int treatmentPeriodDay;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}
