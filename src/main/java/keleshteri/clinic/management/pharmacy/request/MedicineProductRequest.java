package keleshteri.clinic.management.pharmacy.request;

import keleshteri.clinic.management.global.units_measurement.UnitsMeasurement;
import keleshteri.clinic.management.pharmacy.model.Medicine;
import keleshteri.clinic.management.pharmacy.model.MedicineCompany;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MedicineProductRequest {

    @NotNull(message = "Please enter  code")
    private Integer code;

    @NotNull(message = "Please enter  Name")
    private String name;



    private Long medicine;



    private Long company;



    private Long units;


    private Double  dose;
}
