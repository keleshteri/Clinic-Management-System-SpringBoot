package keleshteri.clinic.management.identity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@Entity
@Table(name = "identity")
public class Identity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "Please enter Identity Type")
    @Column(nullable = false)
    private IdentityType type;

    @NotNull(message = "Please upload image")
    @Column(nullable = false)
    private String image;




}
