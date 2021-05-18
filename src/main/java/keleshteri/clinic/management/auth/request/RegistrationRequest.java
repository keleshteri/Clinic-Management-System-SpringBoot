package keleshteri.clinic.management.auth.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {


    private String username;

    private String email;

    @NotNull(message = "Registration password cannot be null")
    private String password;
}
