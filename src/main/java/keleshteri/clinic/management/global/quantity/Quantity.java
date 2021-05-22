package keleshteri.clinic.management.global.quantity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@Table(name = "quantities")
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please enter  Quantity Name")
    @Column(unique = true,nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

}
