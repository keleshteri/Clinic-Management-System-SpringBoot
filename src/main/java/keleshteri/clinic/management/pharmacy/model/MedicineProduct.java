package keleshteri.clinic.management.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import keleshteri.clinic.management.global.units_measurement.UnitsMeasurement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@EqualsAndHashCode

@Entity
@Table(name = "medicine_products")
public class MedicineProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(nullable = false,unique = true)
    private Integer code;

    @NotNull(message = "Please enter  Name")
    @Column(unique = true,nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "medicine_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "medicine_company_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MedicineCompany medicineCompany;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "medicine_type_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MedicineType medicineType;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "dose_units_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UnitsMeasurement doseUnits;

    @Column(precision = 5, scale = 2)
    private Double  dose;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


    //get


    public String getMedicineName() {
        return medicine.getName();
    }

    public String getMedicineCompanyName() {
        return medicineCompany.getName();
    }

    public String getDoseUnitsName() {
        return doseUnits.getName();
    }

    public String getMedicineTypeName() {
        return medicineType.getName();
    }
}
