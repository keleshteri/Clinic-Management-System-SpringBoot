package keleshteri.clinic.management.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    //

    public Permission() {
    }

    public Permission(String name, String slug) {
        this.name = name;
        this.slug = slug;
        this.active=true;
    }


}
