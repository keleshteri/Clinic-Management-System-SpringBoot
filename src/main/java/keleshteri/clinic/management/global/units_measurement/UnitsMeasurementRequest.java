package keleshteri.clinic.management.global.units_measurement;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UnitsMeasurementRequest {

    @NotNull(message = "Please enter  Units Name")
    private String name;

    private String description;

    @NotNull(message = "Please enter  quantity type")
    private Long quantity;
}
