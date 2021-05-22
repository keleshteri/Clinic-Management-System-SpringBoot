package keleshteri.clinic.management.pharmacy.model;

import keleshteri.clinic.management.global.units_measurement.UnitsMeasurement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
@Entity
@Table(name = "medicine_products")
public class MedicineProduct {

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
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "medicine_company_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MedicineCompany medicineCompany;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "dose_units_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UnitsMeasurement doseUnits;

    @Column(precision = 5, scale = 2)
    private Double  dose;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


}
