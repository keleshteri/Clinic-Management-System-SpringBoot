package keleshteri.clinic.management.global.units_measurement;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@Table(name = "units_measurements")
public class UnitsMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please enter  Units Name")
    @Column(unique = true,nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
