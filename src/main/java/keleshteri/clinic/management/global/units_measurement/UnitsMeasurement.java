package keleshteri.clinic.management.global.units_measurement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import keleshteri.clinic.management.global.quantity.Quantity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@Entity
@Table(name = "units_measurements")
public class UnitsMeasurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "quantity_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    @JsonIgnoreProperties(value = "unitsMeasurements", allowGetters = true)
    private Quantity quantity;

    @NotNull(message = "Please enter  Units Name")
    @Column(unique = true,nullable = false)
    private String name;

    @Column(name = "description")
    private String description;



    public String getQuantity() {
        return quantity.getName();
    }


}
