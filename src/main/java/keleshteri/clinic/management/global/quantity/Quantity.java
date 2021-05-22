package keleshteri.clinic.management.global.quantity;

import keleshteri.clinic.management.global.units_measurement.UnitsMeasurement;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "quantities")
public class Quantity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please enter  Quantity Name")
    @Column(unique = true,nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
    //
    @OneToMany(mappedBy = "quantity")
    private List<UnitsMeasurement> unitsMeasurements;

    //

    public Quantity() {
    }

    public Quantity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
