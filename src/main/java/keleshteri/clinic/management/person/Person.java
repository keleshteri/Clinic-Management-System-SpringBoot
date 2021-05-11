package keleshteri.clinic.management.person;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@MappedSuperclass
public abstract  class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;


    @NotNull(message = "Please enter firstName")
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = true)
    private String middleName;

    @NotNull(message = "Please enter lastName")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Please enter gender")
    private Gender gender;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "National Id")
    @Size(max = 25, min = 3, message = "{nationalId.invalid}")
    @Column(unique = true)
    private String nationalId;

    private Date birthDate;

    private String homeAddress;

    private String landlineNumberHome;

    private String landlineNumberOffice;

    private String cellphoneNumber;


}
